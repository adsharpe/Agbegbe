import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialsModule } from './module/material/material.module';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './components/shared/header/header.component';
import { FooterComponent } from './components/shared/footer/footer.component';
import { LoginComponent } from './components/shared/login/login.component';
import { AboutComponent } from './components/unshared/about/about.component';
import { ContactUsComponent } from './components/unshared/contact-us/contact-us.component';
import { HomeComponent } from './components/unshared/home/home.component';
import { SearchComponent } from './components/unshared/search/search.component';
import { CustomerProfileComponent } from './components/unshared/customerprofile/customerprofile.component';
import { ClientProfileComponent } from './components/unshared/clientprofile/clientprofile.component';
import { SignupComponent } from './components/shared/signup/signup.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    AboutComponent,
    ContactUsComponent,
    HomeComponent,
    SearchComponent,
    CustomerProfileComponent,
    ClientProfileComponent,
    SignupComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialsModule,
  ],
  providers: [],
  entryComponents: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
