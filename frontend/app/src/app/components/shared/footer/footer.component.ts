import { Component, OnInit } from '@angular/core';
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
      this.siteName = resp.name;
    });
  }

  public siteName : string;

  public year : number;

  ngOnInit(): void {}

}
