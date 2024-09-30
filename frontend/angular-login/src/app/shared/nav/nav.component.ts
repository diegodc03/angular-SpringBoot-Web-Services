import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/authentication/service/loginService/login.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
  isLoggedIn = false;
  userLoginOn:boolean=false;
  constructor(private loginService:LoginService, private router:Router,) { }

  ngOnInit(): void {
    

    this.loginService.currentUserLoginOn.subscribe(
      {
        next:(userLoginOn) => {
          this.userLoginOn=userLoginOn;
        }
      }
    )
  }

  


  logout()
  {
    this.isLoggedIn = false;
    this.loginService.logout();
    this.router.navigate(['/index'])
  }



}
