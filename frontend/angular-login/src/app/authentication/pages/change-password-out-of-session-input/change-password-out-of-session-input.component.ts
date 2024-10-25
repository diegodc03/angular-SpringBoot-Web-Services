import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { PasswordChangeServiceService } from '../../service/passwordChangeService/password-change-service.service';
import { LoginService } from '../../service/loginService/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-change-password-out-of-session-input',
  templateUrl: './change-password-out-of-session-input.component.html',
  styleUrl: './change-password-out-of-session-input.component.css'
})
export class ChangePasswordOutOfSessionInputComponent {
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
        newPassword: this.newPassword?.value!,
        confirmPassword: this.confirmPassword?.value!
      };
      console.log(payload);
      this.passwordChangeService.changePasswordOutOfSession(payload);
    }
  }
}
