export class MatchDTO{

    id: number;
    date: Date;
    description: string;
    localTeam: string;
    awayTeam: string;
    seasonId: number;

    constructor(id: number, date: Date, description: string, localTeam: string, awayTeam: string, seasonId: number) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.localTeam = localTeam;
        this.awayTeam = awayTeam;
        this.seasonId = seasonId;
      }

}