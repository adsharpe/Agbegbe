import { Component, OnInit } from '@angular/core';
import { SiteSettings } from 'src/app/class/site-settings';
import { SiteSettingsService } from 'src/app/services/site-settings.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  constructor(private siteSettingsService : SiteSettingsService) {
    this.siteSettingsService.getSiteSettings().subscribe(
      resp => {
      this.siteSettings = resp;
    });
  }

  public siteSettings: SiteSettings;

  ngOnInit(): void {}

}
