import { MatchWithUserInfoDTO } from '../MatchWithUserInfo/MatchWithUserInfo';

export interface WorkedMatchWithUserInfo extends MatchWithUserInfoDTO {
  role: string; // Si `WorkingRoles` es un enum, puedes definirlo aquí como un tipo
  payment: number;

}