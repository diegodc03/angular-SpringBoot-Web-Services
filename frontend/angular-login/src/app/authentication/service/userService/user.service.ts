import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, retry, tap, throwError } from 'rxjs';
import { User } from 'src/app/authentication/model/user/user';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})



export class UserService {

  private apiUrl = `${environment.urlHost}auth/user-data`;  // URL de la API

  constructor(private http:HttpClient) { }

  getUser(id:number):Observable<User>{
    return this.http.get<User>(environment.urlApi+"user/"+id).pipe(
      catchError(this.handleError)
    )
  }

  updateUser(userRequest:User):Observable<any>
  {
    return this.http.put(environment.urlApi+"user", userRequest).pipe(
      catchError(this.handleError)
    )
  }

  private handleError(error:HttpErrorResponse){
    if(error.status===0){
      console.error('Se ha producio un error ', error.error);
    }
    else{
      console.error('Backend retornó el código de estado ', error.status, error.error);
    }
    return throwError(()=> new Error('Algo falló. Por favor intente nuevamente.'));
  }


  getUserData(): Observable<User> {
    return this.http.get<User>(this.apiUrl).pipe(
      tap((userData: User) => {
        console.log('User data:', userData);
      }),
      catchError((error) => {
        console.error('Error getting user data:', error);
        return throwError(error);  // Maneja el error y lo repropaga
      })
    );
  }

}
