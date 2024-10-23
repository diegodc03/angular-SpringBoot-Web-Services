import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Injectable } from '@angular/core';
import { PlayerLeaguesdtoModule } from '../player-leaguesdto/player-leaguesdto.module';
import { Match } from 'src/app/workHours/model/match/Match';
import { PlayMatchDTOWithPlayers } from '../match-dto/match-dto.module';

/*
@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})*/

  export class BaseLeagueDTO {

    name: string;

    constructor( name: string) {
      this.name = name;
    }
  }



  export class CreateLeagueDTO extends BaseLeagueDTO{

    leagueType: string;
    requestToJoinLeague: string;


    constructor(name: string, leagueType: string, requestToJoinLeague: string) {
      super(name);
      this.leagueType = leagueType;
      this.requestToJoinLeague = requestToJoinLeague;
    }
  }


  export class ShowLeagueDTO extends BaseLeagueDTO{

    id: number;


    constructor(name: string, id:number) {
      
      super(name);
      this.id = id;
    }
  }




  export class LeagueDTOModule extends BaseLeagueDTO{
    
    id: number;
    leagueType: string;
    requestToJoinLeague: boolean;

    constructor(id:number, name: string, leagueType: string, requestToJoinLeague: boolean) {
      
      super(name);
      this.id = id;
      this.leagueType = leagueType;
      this.requestToJoinLeague = requestToJoinLeague;
    }
  }


  


  export class LeagueInforamationDTO extends BaseLeagueDTO{
    
    id: number
    requireRequest: boolean;
    playerLeagues: PlayerLeaguesdtoModule[]; // Lista con la información de clasificación de cada jugador
    matchPlayed: PlayMatchDTOWithPlayers[];

    constructor(
      id: number,
      name: string,
      matchPlayed: PlayMatchDTOWithPlayers[],
      playerLeagues: PlayerLeaguesdtoModule[],
      requireRequest: boolean,
    ) {
      super(name);
      this.id = id;
      this.matchPlayed = matchPlayed;
      this.playerLeagues = playerLeagues;
      this.requireRequest = requireRequest;
    }
  }
