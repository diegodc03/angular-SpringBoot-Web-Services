import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { Product} from 'src/app/model/product/product.module';
import { ProductDTO } from 'src/app/model/product-dto/product-dto.module';


@Injectable({
  providedIn: 'root'
})
export class InventaryService {

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

  addProduct(productDTO: ProductDTO): Observable<FormData> {
  
    const formData = new FormData();
    
    // Agregar todos los campos al FormData
    // Agregar el objeto completo como JSON al FormData
    // Serializa el objeto productDTO y añádelo al FormData
    formData.append('product', new Blob([JSON.stringify({
      name: productDTO.name,
      description: productDTO.description,
      price: productDTO.price,
      totalStock: productDTO.totalStock,
      isTshirt: productDTO.isShirt,
      garments: productDTO.garments
  })], { type: 'application/json' }));

  // Añade la imagen si existe
  if (productDTO.image) {
      formData.append('image', productDTO.image);
  }

    console.log(formData.get('product'));
    console.log(formData.get('image'));

    return this.http.post<any>(`${this.apiURL}/add-product`, formData)
    .pipe(
      tap((response) => {
        console.log('Producto creado con éxito:', response);
      }),
      catchError(this.handleError)
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
    return this.http.get(`${this.apiURL}/image/${publicId}`, { responseType: 'blob' });
  }
 

}
