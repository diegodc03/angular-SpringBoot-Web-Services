import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SeasonDTO } from '../../modelDTO/season-dto/SeasonDTO';
import { SeasonService } from '../../service/seasonService/season-service.service';
import { AddMatchDTO } from '../../modelDTO/addMatchDTO/addMatchDTO';
import { MatchDTO } from '../../modelDTO/MatchDTO/MatchDTO';

@Component({
  selector: 'app-add-season',
  templateUrl: './add-season.component.html',
  styleUrl: './add-season.component.css'
})
export class AddSeasonComponent {
  
  fileContent: string | null = null; // Para almacenar el contenido del archivo

  seasonError: string = "";
  fileError: string = "";
  
  seasonForm = this.fb.group({
    seasonName: ['', Validators.required],
  });
  
  seasons: SeasonDTO[] = [];
  
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private seasonService: SeasonService
  ) {}

  
  ngOnInit(): void {

    this.seasonService.getAllSeasons().subscribe(seasons => {
      this.seasons = seasons;
    });

  }
  
  
  onSubmit() {
    if (this.seasonForm.valid && this.fileContent) {
      const formData = new FormData();
      
      // Adjuntar el contenido del archivo como texto
      formData.append('fileContent', this.fileContent);

      // Adjuntar el nombre de la temporada
      formData.append('seasonName', this.seasonForm.value.seasonName || '');

      console.log('Formulario enviado:', this.seasonForm.value);
      console.log('Contenido del archivo enviado:', formData.get('fileContent'));
      console.log('Nombre de la temporada enviado:', formData.get('seasonName'));
      
      this.seasonService.addSeasonWithFile(formData).subscribe(
        response => {
          console.log('Season added:', response);
          this.router.navigateByUrl('/show-seasons');
        }
      );
    
    } else {
      if (!this.fileContent) {
        this.fileError = 'Debes seleccionar y cargar un archivo de texto.';
      }
      console.error('Formulario no válido o archivo no seleccionado');
    }
  }


  onFileChange(event: Event) {
    const input = event.target as HTMLInputElement;

    if (input.files && input.files[0]) {
      const file = input.files[0];

      if (file.type === 'text/plain') {
        const reader = new FileReader();

        reader.onload = (e) => {
          this.fileContent = reader.result as string;
          console.log('Contenido del archivo:', this.fileContent); // Puedes procesar el contenido como necesites
        };

        reader.readAsText(file);
      } else {
        console.error('Por favor, carga un archivo de texto.');
      }
    }
  }



  get seasonName() {
    return this.seasonForm.controls.seasonName;
  }

}
