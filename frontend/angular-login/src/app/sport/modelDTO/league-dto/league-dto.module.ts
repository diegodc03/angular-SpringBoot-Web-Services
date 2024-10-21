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

    requestToJoinLeague: string;

    constructor(name: string, requestToJoinLeague: string) {
      super(name);
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
    requestToJoinLeague: boolean;

    constructor(name: string, id:number, requestToJoinLeague: boolean) {
      
      super(name);
      this.id = id;
      this.requestToJoinLeague = requestToJoinLeague;
    }
  }


  


  export class LeagueInforamationDTO extends BaseLeagueDTO{
    
    id: number
    requestToJoinLeague: boolean;
    players: PlayerLeaguesdtoModule[]; // Lista con la información de clasificación de cada jugador
    matchs: PlayMatchDTOWithPlayers[];

    constructor(
      id: number,
      name: string,
      requestToJoinLeague: boolean,
      matchs: PlayMatchDTOWithPlayers[],
      players: PlayerLeaguesdtoModule[]
    ) {
      super(name);
      this.id = id;
      this.requestToJoinLeague = requestToJoinLeague;
      this.matchs = matchs;
      this.players = players;
    }
  }
