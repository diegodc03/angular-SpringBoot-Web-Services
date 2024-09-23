export class AddMatchDTO{

    date: Date;
    localTeam: string;
    awayTeam: string;
    seasonId: number;

    constructor(date: Date, localTeam: string, awayTeam: string, seasonId: number) {
        this.date = date;
        this.localTeam = localTeam;
        this.awayTeam = awayTeam;
        this.seasonId = seasonId;
      }

}