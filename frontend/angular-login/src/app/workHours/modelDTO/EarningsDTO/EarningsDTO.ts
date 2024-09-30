import { SeasonDTO } from "../season-dto/SeasonDTO";

export class EarningsDTO {
    

    season: SeasonDTO;

    totalMoneyPaid: number;

    moneyToPay: number;

    moneyPaid: number;

    constructor(season: SeasonDTO, totalMoneyPaid: number, moneyToPay: number, moneyPaid: number) {
        this.season = season;
        this.totalMoneyPaid = totalMoneyPaid;
        this.moneyToPay = moneyToPay;
        this.moneyPaid = moneyPaid;
    }

    


}