import { Component, OnInit  } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from 'src/app/services/auth/login.service';
import { PasswordChangeServiceService } from 'src/app/services/auth/passwordChangeService/password-change-service.service';

@Component({
  selector: 'app-password-change',
  templateUrl: './password-change.component.html',
  styleUrls: ['./password-change.component.css']
})
export class ChangePasswordComponent implements OnInit {
  
  constructor(private formBuilder:FormBuilder, private router:Router, private passwordChangeService: PasswordChangeServiceService, private loginService: LoginService ) { }
  errorMessage: string = '';
  
  changePasswordForm = this.formBuilder.group({
    currentPassword: ['', Validators.required],
    newPassword: ['', Validators.required],
    confirmPassword: ['', Validators.required],
  });
  
  

  ngOnInit(): void {
  }

  get currentPassword() {
    //return this.changePasswordForm.get('currentPassword');
    return this.changePasswordForm.get('currentPassword');
  }

  get newPassword() {
    return this.changePasswordForm.get('newPassword');

  }

  get confirmPassword() {
    return this.changePasswordForm.get('confirmPassword');
  }

  

  onSubmit() {
    if (this.changePasswordForm.valid) {
      const payload = {
        currentPassword: this.currentPassword?.value!,
        newPassword: this.newPassword?.value!,
        confirmPassword: this.confirmPassword?.value!
      };
      
      

      this.passwordChangeService.changePassword(payload).subscribe({
        next: (response) => {
          if (response.error) {
            this.errorMessage = response.error;
            console.error('Error:', response.error);
          } else {
            console.log('Contraseña correctamente cambiada:', response);
            this.loginService.logout();
            this.router.navigate(['/login']);
          }
        },
        error: (error) => {
          console.error('No se ha conseguido cambiar la contraseña:', error);
          // Maneja el error (p.ej., redirige al usuario al login)
          this.loginService.logout();
         
        }
      });

    }
  }
}