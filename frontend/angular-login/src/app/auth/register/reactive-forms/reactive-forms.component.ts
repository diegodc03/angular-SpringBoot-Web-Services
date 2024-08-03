import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegistrationService } from 'src/app/services/auth/register/register.service';
import { RegistrationRequest } from 'src/app/services/auth/register/registrationRequest';

@Component({
  selector: 'app-registration',
  templateUrl: './reactive-forms.component.html',
  styleUrls: ['./reactive-forms.component.css']
})
export class ReactiveFormsComponent implements OnInit {
  registrationError: string = "";
  registerForm = this.formBuilder.group({
    username: ['', [Validators.required, Validators.email]],
    firstname: ['', Validators.required],
    lastname: ['', Validators.required],
    country: ['', Validators.required],
    password: ['', Validators.required]
  });

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private registrationService: RegistrationService
  ) { }

  ngOnInit(): void { }

  get username() {
    return this.registerForm.controls.username;
  }

  get firstname() {
    return this.registerForm.controls.firstname;
  }

  get lastname() {
    return this.registerForm.controls.lastname;
  }

  get country() {
    return this.registerForm.controls.country;
  }

  get password() {
    return this.registerForm.controls.password;
  }

  onSubmit() {
    if (this.registerForm.valid) {
      this.registrationError = "";
      this.registrationService.register(this.registerForm.value as RegistrationRequest).subscribe({
        next: (response) => {
          console.log('Registration successful:', response);
          this.router.navigateByUrl('/login');
        },
        error: (error) => {
          console.error('Registration error:', error);
          this.registrationError = 'Error during registration. Please try again.';
        }
      });
    } else {
      this.registerForm.markAllAsTouched();
      alert("Please correct the errors in the form.");
    }
  }
}

