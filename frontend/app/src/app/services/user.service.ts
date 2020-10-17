import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from '../class/user';
import { UrlService, UrlType } from './url.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private urlService : UrlService, private httpClient : HttpClient) {
    this.userUrl = this.urlService.getUrl(UrlType.URL_USER);
  }

  private userUrl : string;

  getUser(username : string) : Observable<User> {
    let userUrl : string = this.userUrl + '?username=' + username;

    return this.httpClient.get<User>(userUrl).pipe(
      map(
        resp => {
          return resp;
      })
    );
  }
}
