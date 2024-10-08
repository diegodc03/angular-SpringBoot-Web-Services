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
export class LeagueDTOModule {

  id: number;
  name: string;
  requestToJoinLeague: boolean;

  constructor(id: number, name: string, requestToJoinLeague: boolean) {
    this.id = id;
    this.name = name;
    this.requestToJoinLeague = requestToJoinLeague;
  }


 }
