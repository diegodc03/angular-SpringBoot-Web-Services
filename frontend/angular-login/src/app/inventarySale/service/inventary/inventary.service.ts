import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, of, switchMap, tap, throwError } from 'rxjs';
import { Product} from '../../model/product/product.module';
import { ProductDTO } from '../../modelDTO/product-dto/product-dto.module';


@Injectable({
  providedIn: 'root'
})
export class InventaryService {
  productId: string = '';
  private apiImageURL = "http://localhost:8081/inventary";
  private apiURL = "http://localhost:8080/inventary";

  constructor(private http: HttpClient) { }

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiURL}/all-inventary`);
  }

  getProductById(publicIdd: String): Observable<Product> {
    return this.http.get<Product>(`${this.apiURL}/inventary/${publicIdd}`);
  }

  getProductsByPriceAscending(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiURL}/price-ascending`);
  }

  getProductsByPriceDescending(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiURL}/price-descending`);
  }

  getProductsByStockAscending(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiURL}/stock-ascending`);
  }

  getProductsByStockDescending(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiURL}/stock-descending`);
  }

  addProduct(productDTO: ProductDTO): Observable<String> {
    
    //Enviamos la informacion del producto sin la imagen en un objeto JSON
    //y la imagen en un objeto FormData
  /**
   * Explicación Detallada:
   *
   * RxJS y Observables:
   * - Observable: Es una estructura que representa una secuencia de datos o eventos que se pueden emitir de manera asíncrona.
   * - Suscripción: Necesitas suscribirte a un Observable para empezar a recibir sus datos emitidos.
   *
   * Operadores de RxJS:
   * - Operadores de Transformación: Como `map`, `filter`, `switchMap`, que transforman los datos emitidos por un Observable.
   * - `pipe`: Permite encadenar varios operadores para transformar los datos emitidos por un Observable.
   *
   * Problema Original:
   * - Intentabas hacer dos peticiones HTTP en secuencia: primero, crear un producto; luego, subir una imagen relacionada.
   * - Sin embargo, al intentar retornar una nueva llamada HTTP desde el operador `tap`, estabas interrumpiendo el flujo de datos.
   *
   * ¿Por Qué No Funciona el Código Original?
   * - `tap`: Este operador se usa para ejecutar efectos secundarios (como un `console.log`) sin modificar el flujo de datos del Observable.
   *   No está diseñado para cambiar o interrumpir el flujo. Si retornas un nuevo Observable dentro de `tap`, este no será parte del flujo
   *   principal y será ignorado.
   * - Al retornar un nuevo Observable dentro de `tap`, el flujo de datos original se interrumpe, causando que la segunda petición no se ejecute
   *   como esperabas.
   *
   * Solución - Uso de `switchMap`:
   * - `switchMap`: Es un operador que se utiliza para encadenar operaciones asincrónicas de manera correcta.
   * - Cuando `switchMap` recibe un valor de un Observable, lo usa para crear un nuevo Observable y se suscribe automáticamente a este nuevo
   *   Observable.
   * - `switchMap` también cancela cualquier Observable anterior si el Observable original emite un nuevo valor antes de que el Observable interno
   *   se complete.
   *
   * Código Reescrito con `switchMap`:
   * - Utilizamos `switchMap` para encadenar la segunda petición HTTP (subir la imagen) después de que la primera petición (crear el producto) se
   *   haya completado.
   * - Esto permite que la segunda petición dependa del resultado de la primera sin interrumpir el flujo del Observable.
   *
   * Beneficios:
   * - Encadenamiento Correcto: `switchMap` asegura que las operaciones asíncronas se encadenen correctamente sin interrumpir el flujo de datos.
   * - Manejo de Errores: Se pueden manejar errores en cada etapa del flujo usando `catchError`.
   * - Flujo Continuo: `switchMap` garantiza que el flujo de datos se mantenga fluido, lo cual es crucial para operaciones asíncronas como
   *   múltiples peticiones HTTP.
   *
   * En Resumen:
   * - Este enfoque permite un manejo más predecible y controlado de flujos asíncronos en Angular utilizando RxJS.
   */
   

    // Primera petición: enviar la información del producto
    return this.http.post<string>(`${this.apiURL}/add-product-info`, productDTO, { responseType: 'text' as 'json' })
    .pipe(
      tap((productId: string) => {
        
        console.log('Producto agregado con éxito:', productId);
        this.productId = productId;
      
      }),
      catchError(this.handleError) // Manejo de errores en la primera y segunda petición
    );
      
  }



  private handleError(error: HttpErrorResponse): Observable<never> {
    console.error('Ocurrió un error:', error);
    return throwError(() => new Error('Algo salió mal; por favor, intente de nuevo más tarde.'));
  }

  updateProduct(publicId: String, productDTO: ProductDTO): Observable<Product> {
    return this.http.put<Product>(`${this.apiURL}/update-product/${publicId}`, productDTO);
  }

  deleteProduct(publicId: string): Observable<void> {
    console.log(publicId);
    return this.http.delete<void>(`${this.apiURL}/delete-product/${publicId}`);
  }

  getProductImage(publicId: string): Observable<Blob> {
    return this.http.get(`${this.apiURL}/image/${publicId}`, { responseType: 'blob'  });
  }
 

}
