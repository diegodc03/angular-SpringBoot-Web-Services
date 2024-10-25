import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';




export class LeagueIdDTOModule { 

  leagueId: number;


  constructor(
    leagueId: number
  ) {
    this.leagueId = leagueId;
  }



}
