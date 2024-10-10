import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Injectable } from '@angular/core';

/*
@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})*/

export class BaseLeagueDTO {
  id: number;
  name: string;

  constructor(id: number, name: string) {
    this.id = id;
    this.name = name;
  }
}





export class LeagueDTOModule extends BaseLeagueDTO{

  requestToJoinLeague: boolean;

  constructor(id: number, name: string, requestToJoinLeague: boolean) {
    
    super(id, name);
    this.requestToJoinLeague = requestToJoinLeague;
  }


 }
