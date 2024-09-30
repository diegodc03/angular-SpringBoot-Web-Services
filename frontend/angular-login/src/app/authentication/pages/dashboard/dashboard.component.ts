import { Component, OnDestroy, OnInit } from '@angular/core';
import { LoginService } from 'src/app/authentication/service/loginService/login.service';
import { User } from 'src/app/authentication/model/user/user';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})

export class DashboardComponent implements OnInit {

  welcomeMessage = "Welcome to the dashboard!";
  errorMessage = "Error to get the welcome message";

  userLoginOn:boolean=false;
  constructor(private loginService:LoginService) { }

  ngOnInit(): void {
    this.loginService.currentUserLoginOn.subscribe({
      next:(userLoginOn) => {
        this.userLoginOn=userLoginOn;
      }
    });

  }

}

