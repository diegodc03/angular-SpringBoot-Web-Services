import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  public isAdmin(): boolean {
    const token = sessionStorage.getItem('token'); // Supongamos que el token se guarda en localStorage
    if (!token) return false;

    const decodedToken: any = jwtDecode(token);
    console.log('Decoded token:', decodedToken);
    // Aquí verificamos si el rol es ADMIN
    return decodedToken.role === 'ADMIN';
  }
  
}
