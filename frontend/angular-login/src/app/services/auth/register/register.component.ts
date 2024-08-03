import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterService } from 'src/app/services/auth/register/register.service';



@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm: FormGroup;
  registerError: string | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private registerService: RegisterService,
    private router: Router
  ) {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      country: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  get username() { return this.registerForm.get('username'); }
  get firstname() { return this.registerForm.get('firstname'); }
  get lastname() { return this.registerForm.get('lastname'); }
  get country() { return this.registerForm.get('country'); }
  get password() { return this.registerForm.get('password'); }

  onSubmit() {
    if (this.registerForm.invalid) {
      return;
    }

    const payload: RegisterRequest = this.registerForm.value;

    this.registerService.register(payload).subscribe({
      next: () => {
        this.router.navigate(['/login']);
      },
      error: (error) => {
        this.registerError = 'Error en el registro';
      }
    });
  }
}
