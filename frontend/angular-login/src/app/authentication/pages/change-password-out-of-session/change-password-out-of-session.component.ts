import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PasswordChangeServiceService } from '../../service/passwordChangeService/password-change-service.service';
import { LoginService } from '../../service/loginService/login.service';

@Component({
  selector: 'app-change-password-out-of-session',
  templateUrl: './change-password-out-of-session.component.html',
  styleUrl: './change-password-out-of-session.component.css'
})
export class ChangePasswordOutOfSessionComponent {
  email: any;
  
  constructor(private formBuilder:FormBuilder, private router:Router, private passwordChangeService: PasswordChangeServiceService, private loginService: LoginService ) { }
  errorMessage: string = '';
  
  changePasswordForm = this.formBuilder.group({
    email: ['', Validators.required],
  });
  
  

  get currentPassword() {
    //return this.changePasswordForm.get('currentPassword');
    return this.changePasswordForm.get('email');
  }
  

  onSubmit() {
    
    if (this.changePasswordForm.valid) {
      const payload = {
        email: this.changePasswordForm.get('email')?.value!,
      };
      console.log(payload);
      this.passwordChangeService.sendEmailchangePasswordOutOfSession(payload);

    }
  }
}
