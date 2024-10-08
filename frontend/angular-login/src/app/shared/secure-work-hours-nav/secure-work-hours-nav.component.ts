import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/authentication/service/loginService/login.service';

@Component({
  selector: 'app-secure-work-hours-nav',
  templateUrl: './secure-work-hours-nav.component.html',
  styleUrl: './secure-work-hours-nav.component.css'
})
export class SecureWorkHoursNavComponent {

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
