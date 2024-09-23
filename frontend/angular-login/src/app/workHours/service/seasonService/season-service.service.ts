import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SeasonDTO } from '../../modelDTO/season-dto/SeasonDTO';
import { MatchWithUserInfoDTO } from '../../modelDTO/MatchWithUserInfo/MatchWithUserInfo';
import { WorkedMatchWithUserInfo } from '../../modelDTO/MatchWithUserInfo/WorkedMatchWithUserInfo';
import { EarningsDTO } from '../../modelDTO/EarningsDTO/EarningsDTO';
import { PaidMoneyRequestDTO } from '../../modelDTO/PaidMoneyRequestDTO/PaidMoneyRequestDTO';
import { AddMatchDTO } from '../../modelDTO/addMatchDTO/addMatchDTO';

@Injectable({
  providedIn: 'root'
})
export class SeasonService {
  
  

  private apiURL = "http://localhost:8080";
  

  constructor(private http: HttpClient) { }


  getAllSeasons(): Observable<SeasonDTO[]> {
    return this.http.get<SeasonDTO[]>(`${this.apiURL}/season/all-seasons`);
  }

  deleteWork(matchId: number): Observable<any> {
    return this.http.delete(`${this.apiURL}/work-hours/userMatches/delete-worked-match/${matchId}`, { responseType: 'text' as 'json' });
  }

  addSeasonWithFile(formData: FormData): Observable<string> {
    return this.http.post<string>(`${this.apiURL}/work-hours/add-season`, formData);
  }

}
