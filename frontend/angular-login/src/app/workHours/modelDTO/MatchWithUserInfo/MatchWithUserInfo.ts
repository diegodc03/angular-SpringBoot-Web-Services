import { MatchDTO } from "../MatchDTO/MatchDTO";

export interface MatchWithUserInfoDTO {
    match: MatchDTO;
    userWorked: boolean;
}