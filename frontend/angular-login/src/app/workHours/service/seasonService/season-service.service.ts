import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SeasonDTO } from '../../modelDTO/season-dto/SeasonDTO';
import { MatchWithUserInfoDTO } from '../../modelDTO/MatchWithUserInfo/MatchWithUserInfo';
import { WorkedMatchWithUserInfo } from '../../modelDTO/MatchWithUserInfo/WorkedMatchWithUserInfo';
import { EarningsDTO } from '../../modelDTO/EarningsDTO/EarningsDTO';

@Injectable({
  providedIn: 'root'
})
export class SeasonService {
  
  

  private apiURL = "http://localhost:8080";
  

  constructor(private http: HttpClient) { }


  getAllSeasons(): Observable<SeasonDTO[]> {
    return this.http.get<SeasonDTO[]>(`${this.apiURL}/season/all-seasons`);
  }


  getMatchesBySeasonId(seasonId: number): Observable<(MatchWithUserInfoDTO | WorkedMatchWithUserInfo)[]> {
    return this.http.get<(MatchWithUserInfoDTO | WorkedMatchWithUserInfo)[]>(`${this.apiURL}/work-hours/userMatches/get-all-matches-of-season-with-user-info/${seasonId}`);
  }

  getEarningsBySeasonId(selectedSeasonId: number): Observable<EarningsDTO> {
    return this.http.get<EarningsDTO>(`${this.apiURL}/moneyEarnings/get-arnings-by-seasonId/${selectedSeasonId}`);
  }


}
