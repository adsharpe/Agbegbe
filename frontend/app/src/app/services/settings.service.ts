import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Setting } from '../class/setting';
import { UrlService, UrlType } from './url.service';

@Injectable({
  providedIn: 'root'
})
export class SettingsService {

  constructor(private urlService : UrlService, private httpClient : HttpClient) {
    this.settingsUrl = this.urlService.getUrl(UrlType.URL_SETTINGS);
  }

  private settingsUrl : string;

  addSetting(setting : Setting) : Observable<Setting> {

    return this.httpClient.post<Setting>(this.settingsUrl, { body : setting}).pipe(
      map(
        resp => {
          console.log('resp.name=', resp.name);
          console.log('resp.value=', resp.value);
          return resp;
        }
      )
    );
  }

  getSetting(name : string) : Observable<Setting> {
    let settingsUrl : string = this.settingsUrl + '?name=' + name;

    return this.httpClient.get<Setting>(settingsUrl).pipe(
      map(
        resp => {
          console.log('resp.name=', resp.name);
          console.log('resp.value=', resp.value);
          return resp;
        }
      )
    );
  }

  updateSetting(setting : Setting) : Observable<Setting> {
    return this.httpClient.put<Setting>(this.settingsUrl, { body : setting}).pipe(
      map(
        resp => {
          console.log('resp=' + resp);
          return resp;
        }
      )
    );
  }

  deleteSetting(name : string) : Observable<void> {
    let settingsUrl : string = this.settingsUrl + '?name=' + name;

    return this.httpClient.delete<void>(settingsUrl).pipe(
      map(
        resp => {
          console.log('resp=' + resp);
          return resp;
        }
      )
    );
  }
}
