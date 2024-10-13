import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/authentication/service/loginService/login.service';

@Component({
  selector: 'app-secure-sport-nav',
  templateUrl: './secure-sport-nav.component.html',
  styleUrl: './secure-sport-nav.component.css'
})
export class SecureSportNavComponent {

  private loginService: LoginService;
  private router: Router;

  constructor(loginService: LoginService, router: Router) {
    this.loginService = loginService;
    this.router = router;
  }

  logout() {
    this.loginService.logout();
    this.router.navigate(['/index']);
  }
}
