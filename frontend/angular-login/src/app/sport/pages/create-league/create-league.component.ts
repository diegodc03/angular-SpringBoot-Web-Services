import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-league',
  templateUrl: './create-league.component.html',
  styleUrl: './create-league.component.css'
})
export class CreateLeagueComponent {
  
  addLegueError: String = "";
  addLeague: FormGroup;
  
  constructor(private fb: FormBuilder){
    this.addLeague = this.fb.group({
      name: ['', Validators.required],
      requiresRequest: ['', Validators.required],
    });
  }
  
  onSubmit() {
    if (this.addLeague.valid) {
      const leagueData = this.addLeague.value;

      // Aquí puedes enviar leagueData a tu servicio para crear la liga.
      // Asegúrate de que el backend esté preparado para recibir el campo requiresRequest.
      console.log('League data:', leagueData);
    } else {
      this.addLegueError = 'Por favor, complete todos los campos requeridos.';
    }
  }

}
