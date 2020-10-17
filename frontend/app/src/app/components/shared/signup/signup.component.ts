import { Component, OnInit } from '@angular/core';
import { NgModel } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor() { }

  public showPassword : boolean = false;

  ngOnInit(): void {
  }

  togglePrivacy() {
    let passElement1 : HTMLInputElement = <HTMLInputElement>document.getElementById("password");
    let passElement2 : HTMLInputElement = <HTMLInputElement>document.getElementById("password2");

    this.showPassword = !this.showPassword

    if(this.showPassword === true) {
      passElement1.type = "text";
      passElement2.type = "text";
    } else {
      passElement1.type = "password";
      passElement2.type = "password";
    }    
  }

  submitSignup() {
  }

}
