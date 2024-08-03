import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/auth/login.service';
import { Router } from '@angular/router'; // Import the Router


@Component({
  selector: 'app-secure-header',
  templateUrl: './secure-header.component.html',
  styleUrls: ['./secure-header.component.css']
})
export class SecureHeaderComponent implements OnInit {
  
  userLoginOn:boolean=false;
  constructor(private loginService:LoginService, private router:Router) { }
  isLoggedIn = false;
  
  ngOnInit(): void {
    this.handleAuthorization();
  }



  handleAuthorization() {
    var token = sessionStorage.getItem('token');
    console.log("Token desde secureHeader:", token);

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
