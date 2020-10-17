import { Component, OnInit } from '@angular/core';
import { NgModel } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginData } from 'src/app/class/login-data';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService : AuthService, private router : Router) {
    if(this.authService.currentUser !== null) {
      this.router.navigate(['/']);
    }
   }

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
    let loginData : LoginData = new LoginData;

    loginData.username = formUser.value;
    loginData.password = formPass.value;

    this.authService.login(loginData).subscribe(
      resp => {
        console.log('Login successful. Navigating to homepage');
        this.router.navigate(['/']);
    });
  }

}
