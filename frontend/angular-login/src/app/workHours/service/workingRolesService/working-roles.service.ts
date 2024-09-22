import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { WorkingRoleDTO } from '../../modelDTO/WorkingRoleDTO/WorkingRoleDTO';

@Injectable({
  providedIn: 'root'
})
export class WorkingRolesService {

  private apiURL = "http://localhost:8080/working-role";



  constructor(private http: HttpClient) { }



  getWorkingRoles(): Observable<WorkingRoleDTO[]> {
    return this.http.get<WorkingRoleDTO[]>(`${this.apiURL}/get-roles`)
  }



}
