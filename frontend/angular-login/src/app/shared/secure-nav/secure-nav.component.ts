import { Component } from '@angular/core';
import { LoginService } from 'src/app/services/auth/login.service';// Import the LoginService
import { Router } from '@angular/router'; // Import the Router


@Component({
  selector: 'app-secure-nav',
  templateUrl: './secure-nav.component.html',
  styleUrls: ['./secure-nav.component.css']
})
export class SecureNavComponent {
  
  
  userLoginOn:boolean=false;
  constructor(private loginService:LoginService, private router:Router) { }
  isLoggedIn = false;
  
  ngOnInit(): void {
    console.log("SecureNavComponent.ngOnInit()");
   

    this.loginService.currentUserLoginOn.subscribe(
      {
        next:(userLoginOn) => {
          this.userLoginOn=userLoginOn;
        }
      }
    )
  }
  
  logout() {
    this.loginService.logout();
    this.router.navigate(['/index']);
  }

  checkLogin() {
    //quiero escrinir el token
    console.log('Token desde secureNav :', sessionStorage.getItem('token'));
    // Tengo que poner que compruebe en el back el token
  
    this.loginService.checkLogin().subscribe({
      next: (response) => {
        console.log('Token validation response:', response);
        // Maneja la respuesta del backend
      },
      error: (error) => {
        console.error('Token validation error:', error);
        // Maneja el error (p.ej., redirige al usuario al login)
      }
    });


    return this.loginService.isAuthenticated();
  }

  

  


}
