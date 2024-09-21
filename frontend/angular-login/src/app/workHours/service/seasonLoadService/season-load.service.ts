import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SeasonLoadService {

  private seasonId: number | null = null;

  setSeasonId(seasonId: number) {
    this.seasonId = seasonId;
  }

  getSeasonId() {
    return this.seasonId;
  }


  
  
}
