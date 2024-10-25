import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable, of,  throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { LoginService} from 'src/app/authentication/service/loginService/login.service';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PasswordChangeServiceService {
  
  
  private apiUrl = `${environment.urlHost}auth/changePassword`;
  constructor(private http: HttpClient, private loginService: LoginService) { }

  changePassword(payload: { currentPassword: string, newPassword: string, confirmPassword: string }): Observable<any> {
    // Validación local antes de enviar al backend
    const validationError = this.validatePasswords(payload.newPassword, payload.confirmPassword);
    console.log('Validation error:', validationError);

    if (!validationError) {
      return of({ error: "Las contraseñas no coinciden" });
    }

    const currentPassword = payload.currentPassword;
    const newPassword = payload.newPassword

    const token = sessionStorage.getItem('token');
    if (!token) {
      return throwError(() => new Error('No token found. You are not logged in. Please log in to access this page.'));
    }else{
      // Enviar solicitud al backend
      this.loginService.checkLogin();

       // Configurar los encabezados con el token
        const headers = new HttpHeaders({
          'Authorization': `Bearer ${token}`
        });

    
      console.log('Change password service, the last');
      return this.http.post(this.apiUrl, { currentPassword, newPassword }, { headers }).pipe(
        map(response => {
          // Puedes manejar la respuesta aquí si es necesario
          return response;
        }),
        catchError(error => {
          // Manejar errores del backend
          console.error('Error changing password:', error);
          return throwError(() => new Error('Error changing password'));
        })
      );
    
    } 
  }

  private validatePasswords(newPassword: string, confirmPassword: string):boolean {
    console.log(newPassword, confirmPassword);
    if (newPassword !== confirmPassword) {
      return false;
    }

    return true;
  }


  sendEmailchangePasswordOutOfSession(payload: { email: string; }) {
    const email = payload.email;
    return this.http.post(`${this.apiUrl}/send-email-to-change-password`, { email }).subscribe({
      next: (response: any) => {
        if (response) {
          //this.errorMessage = response;
          console.error('Error:', response);
        }
      },
      error: (error: String) => {
        console.error('No se ha conseguido cambiar la contraseña:', error);
        // Maneja el error (p.ej., redirige al usuario al login)
      }
    });
  }


  changePasswordOutOfSession(payload: { newPassword: string; confirmPassword: string; }) {
    const newPassword = payload.newPassword;
    const confirmPassword = payload.confirmPassword;

    return this.http.post(`${this.apiUrl}/change-password-out-of-session`, {confirmPassword , newPassword}).subscribe({
      next: (response: any) => {
        if (response) {
          //this.errorMessage = response;
          console.error('Error:', response);
        }
      },
      error: (error: String) => {
        console.error('No se ha conseguido cambiar la contraseña:', error);
        // Maneja el error (p.ej., redirige al usuario al login)
      }
    });
  }

}