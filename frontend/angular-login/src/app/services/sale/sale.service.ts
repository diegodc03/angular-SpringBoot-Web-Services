import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductSoldDTO } from 'src/app/model/product-sold-dto/product-sold-dto.module';
import { SaleList } from 'src/app/model/sale-list/sale-list.module';

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


  getAllSales(): Observable<SaleList[]> {
    return this.http.get<SaleList[]>(`${this.apiURL}/all-sales`);
  }



}

