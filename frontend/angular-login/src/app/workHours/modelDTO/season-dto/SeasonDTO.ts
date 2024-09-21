export class SeasonDTO {
  public id: number;
  public seasonName: string;

  constructor(id: number, seasonName: string) {
    this.id = id;
    this.seasonName = seasonName;
  }
}
