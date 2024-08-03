import { HttpClient, HttpErrorResponse, HttpHeaders  } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginRequest } from './loginRequest';
import  {  Observable, throwError, catchError, BehaviorSubject , tap, map} from 'rxjs';
import { User } from './user';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  currentUserLoginOn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  currentUserData: BehaviorSubject<String> =new BehaviorSubject<String>("");

  constructor(private http: HttpClient) { 
    this.currentUserLoginOn=new BehaviorSubject<boolean>(sessionStorage.getItem("token")!=null);
    this.currentUserData=new BehaviorSubject<String>(sessionStorage.getItem("token") || "");
  }

  login(credentials:LoginRequest):Observable<any>{
    console.log("LoginService.login");
    return this.http.post<any>(environment.urlHost+"auth/login",credentials).pipe(
      tap( (userData) => {
        sessionStorage.setItem("token", userData.token);
        console.log("LoginService.login tap userData.token: ", userData.token);
        this.currentUserData.next(userData.token);
        this.currentUserLoginOn.next(true);
      }),
      map((userData)=> userData.token),
      catchError(this.handleError)
    );
  }

  logout():void{
    sessionStorage.removeItem("token");
    this.currentUserLoginOn.next(false);
  }

  private handleError(error:HttpErrorResponse){
    if(error.status===0){
      console.error('Se ha producio un error ', error.error);
    }
    else{
      console.error('Backend retornó el código de estado ', error);
    }
    return throwError(()=> new Error('Algo falló. Por favor intente nuevamente.'));
  }

  get userData():Observable<String>{
    return this.currentUserData.asObservable();
  }

  get userLoginOn(): Observable<boolean>{
    return this.currentUserLoginOn.asObservable();
  }

  get userToken():String{
    return this.currentUserData.value;
  }
  
  // if the token is present, the user is authenticated and the method returns true
  isAuthenticated(): boolean {
    return !!sessionStorage.getItem('token');
  }


  // Método para comprobar el token
  checkLogin(): Observable<any> {
    const token = sessionStorage.getItem('token');
    console.log("LoginService.checkLogin token: ", token);
    
    if (!token) {
      // Si no hay token, se considera no autenticado
      return new Observable(observer => {
        observer.next(false);
        observer.complete();
      });
    }

    // Configurar los encabezados con el token
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    // Enviar la solicitud GET para comprobar el token
    return this.http.get<any>(`${environment.urlToken}/validate-token`, { headers });
  }

  
 


}
