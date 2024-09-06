import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError, catchError, BehaviorSubject, tap } from 'rxjs';
import { RegistrationRequest } from '../../../model/registrationRequest'; // Adjust the path as needed
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private registrationStatus: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient) { }

  register(user: RegistrationRequest): Observable<any> {
    return this.http.post<any>(`${environment.urlHost}auth/register`, user).pipe(
      tap(response => {
        // Update registration status or handle response if needed
        this.registrationStatus.next(true); // Assuming registration is successful
      }),
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      console.error('An error occurred:', error.error);
    } else {
      console.error(`Backend returned code ${error.status}, body was:`, error.error);
    }
    return throwError(() => new Error('Something went wrong; please try again later.'));
  }

  get registrationStatus$(): Observable<boolean> {
    return this.registrationStatus.asObservable();
  }
}
