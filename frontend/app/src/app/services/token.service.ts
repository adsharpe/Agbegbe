import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() { }

  addToken(name : string, data : string) {
    sessionStorage.setItem(name, data);
  }

  removeToken(name : string) {
    sessionStorage.removeItem(name);
  }

  getToken(name : string) : string {
    return sessionStorage.getItem(name);
  }

  hasToken(name : string) : boolean {
    return !!sessionStorage.getItem(name);
  }
}