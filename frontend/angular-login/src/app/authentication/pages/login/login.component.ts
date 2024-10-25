import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators} from '@angular/forms';
import { Router, ActivatedRoute  } from '@angular/router';
import { LoginService } from 'src/app/authentication/service/loginService/login.service';
import { LoginRequest } from 'src/app/authentication/modelDTO/loginRequest';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  
  loginError:string="";
  loginSuccess: String="";

  loginForm=this.formBuilder.group({
    username:['',[Validators.required,Validators.email]],
    password: ['',Validators.required],
  })
  constructor(private formBuilder:FormBuilder, private router:Router, private loginService: LoginService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      if (params['error'] === 'passwordChangeFailed') {
        this.loginError = 'No se pudo cambiar la contraseña, no era igual. Por favor, inténtelo de nuevo.';
      }
      
    });

    this.loginService.currentUserLoginOn.subscribe({
      next: (userLoginOn) => {
        if (!userLoginOn) {
          this.router.navigate(['/login']);
        }
      }
    });
  }

  get email(){
    return this.loginForm.controls.username;
  }

  get password()
  {
    return this.loginForm.controls.password;
  }

  login(){
    if(this.loginForm.valid){
      console.log("Login form is valid");
      this.loginError="";
      this.loginService.login(this.loginForm.value as LoginRequest).subscribe({
        next: (userData) => {
          console.log(userData);
        },
        error: (errorData) => {
          console.error(errorData);
          this.loginError=errorData;
        },
        complete: () => {
          console.info("Login completo");
          console.log("Login exitoso, token: "+sessionStorage.getItem('token'));
          this.router.navigateByUrl('/dashboard');
          this.loginForm.reset();
        }
      })

    }
    else{
      this.loginForm.markAllAsTouched();
      alert("Error al ingresar los datos.");
    }
  }


  olvidarPassword() {
    this.router.navigate(['/changePasswordOut']);
  }


}
