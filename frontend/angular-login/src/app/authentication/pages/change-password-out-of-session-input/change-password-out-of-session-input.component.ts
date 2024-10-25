import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { PasswordChangeServiceService } from '../../service/passwordChangeService/password-change-service.service';
import { LoginService } from '../../service/loginService/login.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-change-password-out-of-session-input',
  templateUrl: './change-password-out-of-session-input.component.html',
  styleUrl: './change-password-out-of-session-input.component.css'
})
export class ChangePasswordOutOfSessionInputComponent {
  constructor(private formBuilder:FormBuilder, 
              private router:Router, 
              private passwordChangeService: PasswordChangeServiceService, 
              private loginService: LoginService, 
              private route: ActivatedRoute
            ) { }
  errorMessage: string = '';
  token: string | null = null;
  
  changePasswordForm = this.formBuilder.group({
    newPassword: ['', Validators.required],
    confirmPassword: ['', Validators.required],
  });
  
  ngOnInit(): void {
    // Obtener el token de la URL
    this.token = this.route.snapshot.queryParamMap.get('token');
  }

  get newPassword() {
    return this.changePasswordForm.get('newPassword');
  }

  get confirmPassword() {
    return this.changePasswordForm.get('confirmPassword');
  }
  

  onSubmit() {
    if (this.changePasswordForm.valid && this.token) {
      const payload = {
        newPassword: this.newPassword?.value!,
        confirmPassword: this.confirmPassword?.value!,
        token: this.token // Agregar el token al payload
      };
      console.log(payload);
      this.passwordChangeService.changePasswordOutOfSession(payload);
    } else {
      this.errorMessage = 'Please fill out the form correctly.';
    }
  }
}
