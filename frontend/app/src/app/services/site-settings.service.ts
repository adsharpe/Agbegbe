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
  
  private defaultSiteSettings : SiteSettings = {
    name : `Agbegbe Social Media Software`,
    logo : null,
    copyrightHolder : `Iron Rook Computing, LLC`,
    copyrightYear : 0
  };
  private siteSettings : SiteSettings = null;
  
  getSiteSettings() : Observable<SiteSettings> {
    if(this.siteSettings === null) {
      this.siteSettings = this.defaultSiteSettings;

      console.log("Getting site name.");

      this.settingsService.getSetting('site-name').pipe(
        map(
          resp => {
            console.log('siteSettings=', this.siteSettings);

            if(resp !== null) {
              console.log('site-name=', resp.value);
              this.siteSettings.name = resp.value;
            }
          },
          err => {
            console.log("Error getting site name.");
        })
      );
    }
    return of(this.siteSettings);
  }

}
