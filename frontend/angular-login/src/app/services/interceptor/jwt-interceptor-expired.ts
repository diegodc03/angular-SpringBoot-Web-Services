import { Injectable } from "@angular/core";
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpErrorResponse } from "@angular/common/http";
import { catchError, Observable, throwError } from "rxjs";
import { Router } from "@angular/router";


@Injectable()
export class JwtInterceptorExpired {
    constructor(private router: Router) {}


    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(
            catchError((error: HttpErrorResponse) => {
                if (error.status === 403 || error.status === 401) {
                    this.notificationService('El token ha expirado. Por favor, inicia sesión nuevamente.');
                    this.router.navigate(['/index']);
                }
                return throwError(error);
            })
        );
    }

    notificationService(notification: String) {
        alert(notification);
    }
    


}
