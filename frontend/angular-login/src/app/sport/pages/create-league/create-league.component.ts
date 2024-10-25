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
  requiresRequest: boolean = false;

  
  constructor(private fb: FormBuilder, 
            private leagueService: LeagueService) {
    this.addLeague = this.fb.group({
      name: ['', Validators.required],
      leagueType:['', Validators.required],
      requiresRequest: [false, Validators.required],
    });
  }
  
  onSubmit() {

    if (this.addLeague.valid) {

      const leagueData = this.addLeague.value;
      const createLeagueDTO: CreateLeagueDTO = { name: leagueData.name, leagueType: leagueData.leagueType , requireRequest: this.requiresRequest };
      this.leagueService.createLeague(createLeagueDTO).subscribe({
        next: (response) => {
            alert(response || 'League created successfully.');
            this.addLeagueSuccess = 'League created successfully.';
            
        },
        error: (error) => {
            alert('Error during league creation. Please try again.');
            this.addLegueError = 'Error during league creation. Please try again.';
        }
        });

    } else {
      this.addLegueError = 'Por favor, complete todos los campos requeridos.';
    }
  }


  onChangeRequiresRequest(event: Event) {
    // Obtenemos el valor actual del checkbox
    const isChecked = (event.target as HTMLInputElement).checked;
    this.requiresRequest = isChecked;
    this.addLeague.patchValue({ requiresRequest: isChecked });

  }


}
