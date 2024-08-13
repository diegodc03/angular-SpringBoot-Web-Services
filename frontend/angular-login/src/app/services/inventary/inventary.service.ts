import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
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

  getProductById(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.apiURL}/inventary/${id}`);
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

  addProduct(productDTO: ProductDTO): Observable<Product> {
    return this.http.post<Product>(`${this.apiURL}/add-product`, productDTO);
  }

  updateProduct(id: number, productDTO: ProductDTO): Observable<Product> {
    return this.http.put<Product>(`${this.apiURL}/update-product/${id}`, productDTO);
  }

  deleteProduct(id: number): Observable<void> {
    console.log(id);
    return this.http.delete<void>(`${this.apiURL}/delete-product/${id}`);
  }


}
