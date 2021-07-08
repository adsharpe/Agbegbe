import { Injectable } from '@angular/core';
import { UrlService, UrlType } from './url.service';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { LoginData } from '../class/login-data';
import { TokenService } from './token.service';
import { Login } from '../class/login';
import { Router } from '@angular/router';
import { User } from '../class/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private urlService : UrlService, private tokenService : TokenService,
              private router : Router, private httpClient : HttpClient) {
    this.authUrl = this.urlService.getUrl(UrlType.URL_AUTH);
  }

  private authUrl : string;
  private user_name : string = 'abegbe_username';
  private user_token_name : string = 'abegbe_token';
  private user_refresh_name : string = 'abegbe_refresh';

  private isLoggedIn : BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  private user : BehaviorSubject<User> = new BehaviorSubject<User>(null);

  login(loginData : LoginData) : Observable<Login> {
    console.log(JSON.stringify(loginData));

    return this.httpClient.post<Login>(this.authUrl, loginData).pipe(
      map(
        resp => {
          console.log('resp=', resp);
          this.tokenService.addToken(this.user_token_name, resp.tokenValue);
          this.tokenService.addToken(this.user_refresh_name, resp.tokenRefresh);
          this.tokenService.addToken(this.user_name, resp.username);
          this.setUser(resp.user);
          this.isLoggedIn.next(true);
          return resp;
        }
      )
    );
  }

  refresh() : Observable<Login> {
    let refresh : string = this.tokenService.getToken(this.user_refresh_name);
    let authUrl : string = this.authUrl + '/refresh' + '?refresh=' + refresh;

    return this.httpClient.get<Login>(authUrl).pipe(
      map(
        resp => {
          console.log('resp=', resp);
          this.tokenService.removeToken(this.user_token_name);
          this.tokenService.removeToken(this.user_refresh_name);
          this.tokenService.addToken(this.user_token_name, resp.tokenValue);
          this.tokenService.addToken(this.user_refresh_name, resp.tokenRefresh);
          return resp;
        },
        err => {
          this.clearUser();
          this.router.navigate(['/login']);
          return null;
        }
      )
    );
  }

  logout() : Observable<void> {
    let token : string = this.tokenService.getToken(this.user_token_name);
    let authUrl : string = this.authUrl + '?token=' + token;

    return this.httpClient.delete<void>(authUrl).pipe(
      map(
        resp => {
          this.clearUser();
          this.router.navigate(['/login']);
      })
    );
  }

  isUserValid() : Observable<boolean> {
    let token : string = this.tokenService.getToken(this.user_token_name);
    let authUrl : string = this.authUrl + '/isauth' + '?token=' + token;

    return this.httpClient.get<boolean>(authUrl).pipe(
      map(
        resp => {
          return resp;
      })
    );
  }

  getToken() : string {
    return this.tokenService.getToken(this.user_token_name);
  }

  getRefresh() : string {
    return this.tokenService.getToken(this.user_refresh_name);
  }

  hasToken() : boolean {
    return !!this.tokenService.getToken(this.user_token_name);
  }

  hasRefresh() : boolean {
    return !!this.tokenService.getToken(this.user_refresh_name);
  }

  getUsername() : string {
    return this.tokenService.getToken(this.user_name);
  }

  clearUser() {
    this.tokenService.removeToken(this.user_token_name);
    this.tokenService.removeToken(this.user_refresh_name);
    this.tokenService.removeToken(this.user_name);
    this.setUser(null);
    this.isLoggedIn.next(false);
  }

  setUser(user : User) {
    this.user.next(user);
  }

  get currentUser() : Observable<User> {
    return this.user.asObservable();
  }

  get loginStatus() : Observable<boolean> {
    if(this.hasToken()) {
      this.isLoggedIn.next(true);
    } else {
      this.isLoggedIn.next(false);
    }
    return this.isLoggedIn.asObservable();
  }
}