import { Injectable } from '@angular/core';
import { SettingsService } from './settings.service';
import { SiteSettings } from '../class/site-settings';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SiteSettingsService {

  constructor(private settingsService : SettingsService) {}

  private defaultSiteName : string = 'Agbegbe Community Software';
  
  private siteSettings : SiteSettings = null;
  
  getSiteSettings() : Observable<SiteSettings> {
    if(this.siteSettings === null) {
      return this.settingsService.getSetting('site-name').pipe(
        map(
          resp => {
            console.log('siteSettings=', this.siteSettings);
            this.siteSettings = new SiteSettings();

            if(resp === null) {
              console.log('site-name=', this.defaultSiteName);
              this.siteSettings.name = this.defaultSiteName;
            } else {
              console.log('site-name=', resp.value);
              this.siteSettings.name = resp.value;
            }
            return this.siteSettings;
        })
      );
    } else {
      console.log('siteSettings=', this.siteSettings);
      return of(this.siteSettings);
    }
  }

}
