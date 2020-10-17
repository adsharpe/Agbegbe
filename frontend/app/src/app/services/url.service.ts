import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

export enum HttpHeaderType {
  HTTP_TEXT_PLAIN,
  HTTP_APPL_JSON
};

export enum UrlType {
  URL_AUTH,
  URL_SETTINGS,
  URL_USER,
}

@Injectable({
  providedIn: 'root'
})
export class UrlService {
  constructor() {
    this.httpHeadersJSON  = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Access-Control-Request-Origin' : 'http://localhost:4200',
      'Access-Control-Request-Methods' : ['POST', 'GET', 'PUT', 'DELETE', 'OPTIONS'],
      'Access-Control-Request-Credentials' : 'true'
    });

    this.httpHeadersPlainText  = new HttpHeaders({
      'Content-Type' : 'text/plain',
      'Access-Control-Request-Origin' : 'http://localhost:4200',
      'Access-Control-Request-Methods' : ['POST', 'GET', 'PUT', 'DELETE', 'OPTIONS'],
      'Access-Control-Request-Credentials' : 'true'
    });
  }

  private baseUrl : string = 'http://localhost:8080';
  private authUrl : string = this.baseUrl + '/api/auth';
  private settingsUrl : string = this.baseUrl + '/api/settings';
  private userUrl : string = this.baseUrl + '/api/user';
  
  httpHeadersJSON : HttpHeaders;
  httpHeadersPlainText : HttpHeaders;

  getHeader(headerType : HttpHeaderType) : HttpHeaders {
    let header : HttpHeaders;

    switch(headerType) {
      case HttpHeaderType.HTTP_TEXT_PLAIN: {
        header = this.httpHeadersPlainText;
      } break;
      case HttpHeaderType.HTTP_APPL_JSON: {
        header = this.httpHeadersJSON;
      } break;
      default: header = null;
    }
    return header;
  }

  getUrl(urlType : UrlType) : string {
    let url : string;

    switch(urlType) {
      case UrlType.URL_AUTH: {
        url = this.authUrl;
      } break;
      case UrlType.URL_SETTINGS: {
        url = this.settingsUrl;
      } break;
      case UrlType.URL_USER: {
        url = this.userUrl;
      } break;
      default: url = null;
    }
    return url;
  }
}