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
    formData.append('name', productDTO.name);
    formData.append('description', productDTO.description);
    formData.append('price', productDTO.price.toString());
    formData.append('totalStock', productDTO.totalStock.toString());
    formData.append('isTshirt', productDTO.isShirt.toString());
    formData.append('garments', JSON.stringify(productDTO.garments));

    if (productDTO.image) {
      formData.append('image', productDTO.image);
    }

    return this.http.post<FormData>(`${this.apiURL}/add-product`, formData)
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

 

}
