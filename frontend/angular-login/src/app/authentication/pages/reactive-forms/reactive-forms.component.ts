import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegistrationService } from 'src/app/authentication/service/registerService/register.service';
import { RegistrationRequest } from '../../modelDTO/registrationRequest';

@Component({
  selector: 'app-registration',
  templateUrl: './reactive-forms.component.html',
  styleUrls: ['./reactive-forms.component.css']
})
export class ReactiveFormsComponent implements OnInit {
  registrationError: string = "";
  registerForm = this.formBuilder.group({
    email: ['', [Validators.required, Validators.email]],
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    country: ['', Validators.required],
    password1: ['', Validators.required],
    password2: ['', Validators.required] 
  });

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private registrationService: RegistrationService
  ) { }

  ngOnInit(): void { }

  get email() {
    return this.registerForm.controls.email;
  }

  get firstName() {
    return this.registerForm.controls.firstName;
  }

  get lastName() {
    return this.registerForm.controls.lastName;
  }

  get country() {
    return this.registerForm.controls.country;
  }

  get password1() {
    return this.registerForm.controls.password1;
  }

  get password2() {
    return this.registerForm.controls.password2;
  }

  onSubmit() {
    console.log('Form submitted:', this.registerForm.value);

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

