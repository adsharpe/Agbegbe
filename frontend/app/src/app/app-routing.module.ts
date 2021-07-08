import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/shared/login/login.component';
import { SignupComponent } from './components/shared/signup/signup.component';
import { AboutComponent } from './components/unshared/about/about.component';
import { AccountComponent } from './components/unshared/account/account.component';
import { ContactUsComponent } from './components/unshared/contact-us/contact-us.component';
import { HomeComponent } from './components/unshared/home/home.component';
import { ProfileComponent } from './components/unshared/profile/profile.component';
import { AuthGuard } from './guard/auth.guard';


const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'about', component: AboutComponent },
  { path: 'account', component: AccountComponent },
  { path: 'contact-us', component: ContactUsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'profile/:username', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: 'profile/:username/:category', component: ProfileComponent, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
