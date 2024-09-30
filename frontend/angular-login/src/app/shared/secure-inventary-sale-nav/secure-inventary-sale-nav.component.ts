import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SecureNavComponent } from '../secure-nav/secure-nav.component';
import { LoginService } from 'src/app/authentication/service/loginService/login.service';

@Component({
  selector: 'app-secure-inventary-sale-nav',
  templateUrl: './secure-inventary-sale-nav.component.html',
  styleUrls: ['./secure-inventary-sale-nav.component.css']
})
export class SecureInventarySaleNavComponent {
  

  constructor(private loginService: LoginService, private router: Router) { }


  ngOnInit(): void {
    this.handleAuthorization();
  }

  
  handleAuthorization() {
    if (this.loginService.isAuthenticated()) {
      // Si está autenticado, validar con el servidor
      
    
    } else {
      // Si no hay token, redirigir al login directamente
      console.log('Usuario no autenticado, redirigiendo al login');
      this.router.navigate(['/login']);
    }
  }

  logout() {
    this.loginService.logout();
    this.router.navigate(['/index']);
  }
  
 
}
