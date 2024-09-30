import { Match } from '../match/Match';

export class Season{
    seasonId: number;
    seasonName: String;
    matches: Match[];
  
  
    constructor(seasonId: number, seasonName: String, matches: Match[]) {
        this.seasonName = seasonName;
        this.matches = matches;
        this.seasonId = seasonId;
      }
}

