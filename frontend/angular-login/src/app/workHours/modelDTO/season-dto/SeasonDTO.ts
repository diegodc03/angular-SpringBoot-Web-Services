export class SeasonDTO {
  public publicId: number;
  public seasonName: string;

  constructor(publicId: number, seasonName: string) {
    this.publicId = publicId;
    this.seasonName = seasonName;
  }
}
