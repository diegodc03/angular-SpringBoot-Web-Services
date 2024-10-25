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
    requireRequest: boolean;


    constructor(name: string, leagueType: string, requestToJoinLeague: boolean) {
      super(name);
      this.leagueType = leagueType;
      this.requireRequest = requestToJoinLeague;
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
    requireRequest: boolean;

    constructor(id:number, name: string, leagueType: string, requireRequest: boolean) {
      
      super(name);
      this.id = id;
      this.leagueType = leagueType;
      this.requireRequest = requireRequest;
    }
  }


  


  export class LeagueInforamationDTO extends BaseLeagueDTO{
    
    id: number
    leagueType: string;
    requireRequest: boolean;
    playerLeagues: PlayerLeaguesdtoModule[]; // Lista con la información de clasificación de cada jugador
    matchPlayed: PlayMatchDTOWithPlayers[];

    constructor(
      id: number,
      name: string,
      leagueType: string,
      matchPlayed: PlayMatchDTOWithPlayers[],
      playerLeagues: PlayerLeaguesdtoModule[],
      requireRequest: boolean,
    ) {
      super(name);
      this.id = id;
      this.leagueType = leagueType;
      this.matchPlayed = matchPlayed;
      this.playerLeagues = playerLeagues;
      this.requireRequest = requireRequest;
    }
  }
