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
import { SearchComponent } from './components/shared/search/search.component';
import { SignupComponent } from './components/shared/signup/signup.component';
import { NewsfeedComponent } from './components/unshared/newsfeed/newsfeed.component';
import { AuthService } from './services/auth.service';
import { TokenService } from './services/token.service';
import { SearchService } from './services/search.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptorService } from './services/token-interceptor.service';
import { SettingsService } from './services/settings.service';
import { SiteSettingsService } from './services/site-settings.service';
import { ProfileComponent } from './components/unshared/profile/profile.component';

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
    SignupComponent,
    NewsfeedComponent,
    ProfileComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MaterialsModule,
  ],
  providers: [
    AuthService,
    TokenService,
    SearchService,
    SettingsService,
    SiteSettingsService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }
  ],
  entryComponents: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
