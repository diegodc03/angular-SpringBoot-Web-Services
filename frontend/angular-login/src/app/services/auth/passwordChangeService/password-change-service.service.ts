import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { LoginService} from 'src/app/services/auth/login.service';
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
    if (validationError) {
      return of({ error: validationError });
    }

    const currentPassword = payload.currentPassword;
    const newPassword = payload.newPassword

    const token = sessionStorage.getItem('token');
    if (!token) {
      return of({ error: 'No token found. You are not logged in. Please log in to access this page.' });
    }else{
      // Enviar solicitud al backend
      this.loginService.checkLogin();

       // Configurar los encabezados con el token
        const headers = new HttpHeaders({
          'Authorization': `Bearer ${token}`
        });

    

      return this.http.post(this.apiUrl, { currentPassword, newPassword}, { headers });
    
    }

    
      
  }

  private validatePasswords(newPassword: string, confirmPassword: string): string | null {
    if (newPassword !== confirmPassword) {
      return 'Las contraseñas no coinciden';
    }
    /*
    if (newPassword.length < 8) {
      return 'La contraseña debe tener al menos 8 caracteres';
    }*/
    return null;
  }



}
