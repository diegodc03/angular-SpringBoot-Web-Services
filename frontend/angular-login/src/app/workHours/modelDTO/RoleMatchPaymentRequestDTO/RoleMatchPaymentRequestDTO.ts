 

export class RoleMatchPaymentRequestDTO {

    role: String;
    matchId: number;

    constructor(role: String, matchId: number) {
        this.role = role;
        this.matchId = matchId;
    }


}