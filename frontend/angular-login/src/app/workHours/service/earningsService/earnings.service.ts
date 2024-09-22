import { Injectable } from '@angular/core';
import { PaidMoneyRequestDTO } from '../../modelDTO/PaidMoneyRequestDTO/PaidMoneyRequestDTO';
import { HttpClient } from '@angular/common/http';
import { EarningsDTO } from '../../modelDTO/EarningsDTO/EarningsDTO';
import { Observable } from 'rxjs';
import { RoleMatchPaymentRequestDTO } from '../../modelDTO/RoleMatchPaymentRequestDTO/RoleMatchPaymentRequestDTO';

@Injectable({
  providedIn: 'root'
})
export class EarningsService {
  

  constructor(private http: HttpClient ) { }


  private apiURL = "http://localhost:8080/moneyEarnings";
  private apiURL2 = "http://localhost:8080/work-hours/userMatches";

  addPaidMoney(paidMoneyRequestDTO: PaidMoneyRequestDTO): Observable<EarningsDTO> {
    return this.http.post<EarningsDTO>(`${this.apiURL}/add-paid-money`, paidMoneyRequestDTO);	
  }


  addWork(roleMatchPaymentRequestDTO: RoleMatchPaymentRequestDTO[]): Observable<String> {
    return this.http.post<String>(`${this.apiURL2}/add-payment`, roleMatchPaymentRequestDTO, { responseType: 'text' as 'json' });
  }
  







}
