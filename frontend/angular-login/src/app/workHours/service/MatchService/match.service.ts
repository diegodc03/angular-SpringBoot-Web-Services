import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatchWithUserInfoDTO } from '../../modelDTO/MatchWithUserInfo/MatchWithUserInfo';
import { WorkedMatchWithUserInfo } from '../../modelDTO/MatchWithUserInfo/WorkedMatchWithUserInfo';
import { Observable } from 'rxjs';
import { AddMatchDTO } from '../../modelDTO/addMatchDTO/addMatchDTO';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  constructor(private http: HttpClient) { }
  private apiURL = "http://localhost:8080/work-hours";

  getMatchesBySeasonId(seasonId: number): Observable<(MatchWithUserInfoDTO | WorkedMatchWithUserInfo)[]> {
    console.log("getMatchesBySeasonId");
    return this.http.get<(MatchWithUserInfoDTO | WorkedMatchWithUserInfo)[]>(`${this.apiURL}/userMatches/get-all-matches-of-season-with-user-info/${seasonId}`);
  }

  addMatch(match: AddMatchDTO): Observable<MatchWithUserInfoDTO> {
    return this.http.post<MatchWithUserInfoDTO>(`${this.apiURL}/matches/add-match`, match,{ responseType: 'text' as 'json' });
  }

  deleteMatch(matchId: number): Observable<any> {
    return this.http.delete(`${this.apiURL}/matches/delete-match/${matchId}`, { responseType: 'text' as 'json' });
  }


  getLocalMatchesWithUserInfo(seasonId: number):  Observable<(MatchWithUserInfoDTO | WorkedMatchWithUserInfo)[]> {
    return this.http.get<(MatchWithUserInfoDTO | WorkedMatchWithUserInfo)[]>(`${this.apiURL}/userMatches/get-local-matches/${seasonId}`);
  }

  getAwayMatchesWithUserInfo(seasonId: number):  Observable<(MatchWithUserInfoDTO | WorkedMatchWithUserInfo)[]> {
    return this.http.get<(MatchWithUserInfoDTO | WorkedMatchWithUserInfo)[]>(`${this.apiURL}/userMatches/get-away-matches/${seasonId}`);
  }

  getOnlyWorkedMatches(seasonId: number):  Observable<(WorkedMatchWithUserInfo)[]> {
    return this.http.get<(WorkedMatchWithUserInfo)[]>(`${this.apiURL}/userMatches/get-only-worked-matches/${seasonId}`);
  }

  getOnlyNotWorkedMatches(seasonId: number):  Observable<(MatchWithUserInfoDTO)[]> {
    return this.http.get<(MatchWithUserInfoDTO)[]>(`${this.apiURL}/userMatches/get-only-not-worked-matches/${seasonId}`);
  }



}
