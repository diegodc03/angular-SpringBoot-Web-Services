import { Component } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LoginService } from 'src/app/services/auth/login.service';
import { OnInit } from '@angular/core';

import { Router } from '@angular/router';

@Component({
  selector: 'app-private-zone',
  templateUrl: './private-zone.component.html',
  styleUrls: ['./private-zone.component.css']
})
export class PrivateZoneComponent implements OnInit {

  welcomeMessage: string = 'Welcome to the private zone! You are logged in. You can access this page because you have a valid token.';
  errorMessage: string = 'Error accessing the private zone. Please make sure you are logged in and have a valid token.';

  constructor(private loginService: LoginService, private router: Router ) { }

  ngOnInit(): void {
    this.handleAuthorization();
  }

  handleAuthorization() {
    const token = sessionStorage.getItem('token');
    console.log('Estoy en Handle auth Token:', token);

    if (!token) {
      this.showError('No token found. You are not logged in. Please log in to access this page.');
      this.loginService.logout();
    } else {
      this.showWelcome();
    }
  }  

  showWelcome() {
    this.welcomeMessage = 'Welcome to the private zone! You are logged in. You can access this page because you have a valid token.';
    this.errorMessage = '';
  }

  showError(message: string) {
    this.errorMessage = message;
    this.welcomeMessage = '';
  }
  

  changePassword(){
    console.log('Change password');
    this.router.navigateByUrl('/dashboard/changePassword');
    //WE change the page to change password
  }

  changePersonalInfo(){
    console.log('Change personal info');
  }



  deleteAccount(){
    console.log('Delete account');
    this.loginService.deleteUser();
  }
  



}


