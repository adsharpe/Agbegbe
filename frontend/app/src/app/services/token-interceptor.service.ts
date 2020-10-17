import { Injectable } from '@angular/core';
import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { AuthService } from './auth.service';
import { catchError, switchMap, filter, take } from 'rxjs/operators';
import { throwError, Observable, BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

  constructor(private authServer : AuthService) { }

  private isRefreshing : boolean = false;
  private refreshTokenSubject : BehaviorSubject<any> = new BehaviorSubject<any>([]);

  intercept(request : HttpRequest<any>, next : HttpHandler) : Observable<HttpEvent<any>> {
    if(this.authServer.getToken()) {
      request = this.addToken(request, this.authServer.getToken())
    }
    
    return next.handle(request).pipe(
      catchError(error => {
        if(error instanceof HttpErrorResponse) {
          switch(error.status) {
            case 401: {
              return this.handleError401(request, next);
            };
            default: {
              return throwError(error);
            }
          }
        }
    }));
  }

  handleError401(request : HttpRequest<any>, next : HttpHandler)
  : Observable<HttpEvent<any>> {
    let newToken : string;

    if(!this.isRefreshing) {
      this.isRefreshing = true;
      this.refreshTokenSubject.next(null);

      return this.authServer.refresh().pipe(
        switchMap(
          resp  => {
            this.isRefreshing = false;
            this.refreshTokenSubject.next(this.addToken(request, resp.tokenValue));
            return next.handle(this.addToken(request, resp.tokenValue));
        })
      );
    } else {
      return this.refreshTokenSubject.pipe(
        filter(token => token != null),
        take(1),
        switchMap(
          token => {
            return next.handle(this.addToken(request, token));
        })
      );
    }
  }

  addToken(request : HttpRequest<any>, token : string) : HttpRequest<any> {
    return request.clone({
      setHeaders: {
        Authorization: `Bearer ${this.authServer.getToken()}`
      }
    });
  }
}