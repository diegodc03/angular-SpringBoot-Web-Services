import { Injectable , OnInit, Component} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { LoginService } from '../auth/login.service';

@Component({
  selector: 'app-auth-check-token',
  templateUrl: './auth-check-token.component.html',
  styleUrls: ['./auth-check-token.component.css']
})
export class AuthCheckTokenComponent implements OnInit{
  
  constructor(private loginService: LoginService) { }
  ngOnInit(): void {
    this.checkLoginStatus();
  }
  

  checkLoginStatus(): void {
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
  }
}
