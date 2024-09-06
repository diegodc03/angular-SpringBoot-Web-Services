import { Injectable } from "@angular/core";
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from "@angular/common/http";
import { Observable } from "rxjs";


@Injectable()
export class JwtInterceptor implements HttpInterceptor {


    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

         // Obtén el token del almacenamiento local
        const token = sessionStorage.getItem('authToken');

       
        console.log('URL actual:', req.url);
       
        // Si el token existe y la URL no está excluida, agrega el token al encabezado
        if (token) {
            
            req = req.clone({
                setHeaders: {
                    token: `Bearer ${token}`
                }
            });

            return next.handle(req);
        }

        // Si no hay token, continúa con la solicitud original
        return next.handle(req);

    }



   
}
