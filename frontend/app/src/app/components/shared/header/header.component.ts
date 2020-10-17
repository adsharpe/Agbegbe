import { Component, OnInit } from '@angular/core';
import { MenuItem } from '../../../class/menuitem';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor() { }

  menuItems : MenuItem[] = [
    { name: 'Sign Up', url: 'signup', component: '' },
    { name: 'Login', url: 'login', component: '' },
    { name: 'About', url: 'about', component: '' },
    { name: 'Contact Us', url: 'contact-us', component: '' }
  ];

  ngOnInit(): void {
  }

}
