
export class PaidMoneyRequestDTO {
    seasonId: number;
    moneyPaid: number;



    constructor(seasonId: number, moneyPaid: number) {
        this.seasonId = seasonId;
        this.moneyPaid = moneyPaid;
    }
}