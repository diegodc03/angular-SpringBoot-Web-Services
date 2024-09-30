import { Component } from '@angular/core';
// src/app/guards/auth.guard.ts
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { LoginService } from 'src/app/authentication/service/loginService/login.service';

@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-guard',
  templateUrl: './guard.component.html',
  styleUrls: ['./guard.component.css']
})
export class AuthGuard implements CanActivate {

  constructor(private loginService: LoginService, private router: Router) { }

  canActivate(): boolean {
    if (!this.loginService.isAuthenticated()) {
      console.log('User not authenticated');
      this.router.navigate(['/login']);
      return false;
    }
    console.log('User authenticated');
    return true;
  }
}