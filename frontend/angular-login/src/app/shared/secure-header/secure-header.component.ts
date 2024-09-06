import { Component, Inject, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/auth/login.service';
import { Router } from '@angular/router'; // Import the Router
import { UserService } from 'src/app/services/user/user.service';
import { User } from 'src/app/model/user/user';

@Component({
  selector: 'app-secure-header',
  templateUrl: './secure-header.component.html',
  styleUrls: ['./secure-header.component.css']
})
export class SecureHeaderComponent implements OnInit {
  

  user: User | null = null; // Guarda los detalles del usuario
  isLoggedIn = false;
  name: string = '';
  
  constructor(private loginService:LoginService, private router:Router, @Inject(UserService) private userService: UserService ) { }
  
  
  ngOnInit(): void {
    //this.handleAuthorization();
    this.getUserData();

  }


  getUserData(): void {
    this.userService.getUserData().subscribe({
      next: (userData: User) => {
        this.user = userData;
        this.name = `${userData.firstname} ${userData.lastname}`;
      },
      error: (error: any) => {
        console.error('Error fetching user data:', error);
      }
    });

  }


  handleAuthorization() {


    var token = sessionStorage.getItem('token');
    console.log("Token desde secureHeader:", token);

    if (token) {
      this.loginService.checkLogin().subscribe({
        next: (response) => {
          console.log('Token validation response:', response);
          this.isLoggedIn = true;

          // Obtén los datos del usuario
          this.userService.getUserData().subscribe({
            next: (userData: User) => {
              this.user = userData;
              this.name = `${userData.firstname} ${userData.lastname}`;
            },
            error: (error: any) => {
              console.error('Error fetching user data:', error);
              this.isLoggedIn = false;
            }
          });

        },
        error: (error) => {
          console.error('Token validation error:', error);
          this.isLoggedIn = false;
          this.router.navigate(['/login']); // Redirige al usuario al login si el token es inválido
        }
      });
    } else {
      this.isLoggedIn = false;
      this.router.navigate(['/login']); // Redirige al login si no hay token
    }
  }

}
