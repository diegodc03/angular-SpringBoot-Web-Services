import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductSoldDTO } from '../../modelDTO/product-sold-dto/product-sold-dto.module';
import { SaleList } from '../../model/sale-list/sale-list.module';

@Injectable({
  providedIn: 'root'
})
export class SaleService {
  

  productId: string = '';
  private apiURL = "http://localhost:8080/sale";

  constructor(private http: HttpClient) { }

  

  setNewSale(productsSold: ProductSoldDTO[]): Observable<string> {
    return this.http.post<string>(`${this.apiURL}/add-sale`, productsSold, { responseType: 'text' as 'json' });
  
  }

  getSaleBySaleId(saleId: String): Observable<SaleList> {
    return this.http.get<SaleList>(`${this.apiURL}/${saleId}`);
  }


  getAllSales(): Observable<SaleList[]> {
    return this.http.get<SaleList[]>(`${this.apiURL}/all-sales`);
  }

  deleteSale(saleId: String): Observable<any> {
    return this.http.delete(`${this.apiURL}/delete-sale/${saleId}`);
  }


  deleteProductSale(saleId: String, publicId: String): Observable<any>{
    return this.http.delete(`${this.apiURL}/delete-product-sale/${saleId}/${publicId}`);
  }

}

