export class MatchDTO{

    id: number;
    date: Date;
    description: string;

    constructor(id: number, date: Date, description: string) {
        this.id = id;
        this.date = date;
        this.description = description;
      }

}