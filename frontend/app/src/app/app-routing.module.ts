import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/shared/login/login.component';
import { SignupComponent } from './components/shared/signup/signup.component';
import { AboutComponent } from './components/unshared/about/about.component';
import { ClientProfileComponent } from './components/unshared/clientprofile/clientprofile.component';
import { ContactUsComponent } from './components/unshared/contact-us/contact-us.component';
import { CustomerProfileComponent } from './components/unshared/customerprofile/customerprofile.component';
import { HomeComponent } from './components/unshared/home/home.component';
import { AuthGuard } from './guard/auth.guard';


const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'client-profile', component: ClientProfileComponent, canActivate: [AuthGuard] },
  { path: 'customer-profile', component: CustomerProfileComponent, canActivate: [AuthGuard] },
  { path: 'about', component: AboutComponent },
  { path: 'contact-us', component: ContactUsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
