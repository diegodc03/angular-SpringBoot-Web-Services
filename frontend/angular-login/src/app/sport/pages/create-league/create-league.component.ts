import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LeagueService } from '../../service/leagueService/league.service';
import { CreateLeagueDTO } from '../../modelDTO/league-dto/league-dto.module';
@Component({
  selector: 'app-create-league',
  templateUrl: './create-league.component.html',
  styleUrl: './create-league.component.css'
})
export class CreateLeagueComponent {
  addLeagueSuccess: String = "";  
  addLegueError: String = "";
  addLeague: FormGroup;
  
  constructor(private fb: FormBuilder, private leagueService: LeagueService) {
    this.addLeague = this.fb.group({
      name: ['', Validators.required],
      requiresRequest: [false, Validators.required],
    });
  }
  
  onSubmit() {
    console.log('Form value:', this.addLeague.value);
    console.log('Form valid:', this.addLeague.valid);

    if (this.addLeague.valid) {

      const leagueData = this.addLeague.value;
      const createLeagueDTO: CreateLeagueDTO = { name: leagueData.name, requestToJoinLeague: leagueData.requiresRequest };
      this.leagueService.createLeague(createLeagueDTO).subscribe({
        next: (response) => {
            console.log('league added:', response);
            this.addLeagueSuccess = 'League created successfully.';
            
        },
        error: (error) => {
            console.error('Match error:', error);
            this.addLegueError = 'Error during league creation. Please try again.';
        }
        });
      // Aquí puedes enviar leagueData a tu servicio para crear la liga.
      // Asegúrate de que el backend esté preparado para recibir el campo requiresRequest.
      console.log('League data:', leagueData);
    } else {
      this.addLegueError = 'Por favor, complete todos los campos requeridos.';
    }
  }

}
