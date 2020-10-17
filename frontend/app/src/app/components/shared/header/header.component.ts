import { Component, OnInit } from '@angular/core';
import { MenuItem } from '../../../class/menuitem';
import { SiteSettingsService } from '../../../services/site-settings.service';
import { AuthService } from '../../../services/auth.service';
import { User } from 'src/app/class/user';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private siteSettingsService : SiteSettingsService, private authService : AuthService) {
    this.siteSettingsService.getSiteSettings().subscribe(
      resp => {
        this.siteName = resp.name;
    });
  }

  public siteName : string;
  public currentUser$ : Observable<User>;
  public isLoggedIn$ : Observable<boolean>;

  menuItems : MenuItem[] = [
    { name: 'Profile', url: '', component: '' },
    { name: 'Create', url: '', component: '' },
    { name: 'Messenger', url: '', component: '' },
    { name: 'Notifications', url: '', component: '' },
    { name: 'Account', url: '', component: '' }
  ];

  ngOnInit(): void {
    this.currentUser$ = this.authService.currentUser;
    this.isLoggedIn$ = this.authService.loginStatus;
  }

  logout() {
    this.authService.logout().subscribe();
  }
}
