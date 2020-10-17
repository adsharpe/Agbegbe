import { Component } from '@angular/core';
import { SiteSettingsService } from './services/site-settings.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private siteSettingsService : SiteSettingsService) {
    this.siteSettingsService.getSiteSettings().subscribe(resp => {});
  }

  title = 'app';
}
