import { Component, OnInit } from '@angular/core';
import { NgModel } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor() { }

  public showPassword : boolean = false;

  ngOnInit(): void {
  }

  togglePrivacy() {
    let passElement : HTMLInputElement = <HTMLInputElement>document.getElementById("password");

    this.showPassword = !this.showPassword

    if(this.showPassword === true) {
      passElement.type = "text";
    } else {
      passElement.type = "password";
    }    
  }

  submitLogin(formUser : NgModel, formPass : NgModel) {
  }

}
