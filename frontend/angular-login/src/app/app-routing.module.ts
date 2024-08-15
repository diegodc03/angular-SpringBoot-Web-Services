import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { ReactiveFormsComponent } from './auth/register/reactive-forms/reactive-forms.component';
import { IndexComponent } from './pages/index/index.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { PrivateZoneComponent } from './pages/private-zone/private-zone.component';
import { PublicLayoutComponent } from './layouts/public-layout/public-layout.component';
import { SecureLayoutComponent } from './layouts/secure-layout/secure-layout.component';
import { AuthGuard } from './guards/guard/guard.component';
import { ChangePasswordComponent } from './pages/password-change/password-change.component';
import { SecureInventarySaleLayoutComponent } from './layouts/secure-inventary-sale-layout/secure-inventary-sale-layout.component';
import { InventaryComponent } from './inventary/inventary.component';
import { AddProductInventaryComponent } from './pages/add-product-inventary/add-product-inventary.component';	
import { UpdateProductInventaryComponent } from './pages/update-product-inventary/update-product-inventary.component';
const routes: Routes = [
  {
    path: '',
    component: PublicLayoutComponent,
    children: [
      { path: 'login', component: LoginComponent },
      { path: 'register', component: ReactiveFormsComponent },
      { path: '', redirectTo: 'login', pathMatch: 'full' },
      { path: 'index', component: IndexComponent }
    ]
  },
  {
    path: 'dashboard',
    component: SecureLayoutComponent,
    canActivate: [AuthGuard],
    children: [
      { path: 'dashboard', component: DashboardComponent },
      { path: 'privateServices', component: PrivateZoneComponent },
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'changePassword', component: ChangePasswordComponent }
    ]
  },
  {
    path: 'dashboard/inventarySale',
    component: SecureInventarySaleLayoutComponent,
    canActivate: [AuthGuard],
    children: [
      { path: 'inventary', component: InventaryComponent },
      { path: 'add-product', component: AddProductInventaryComponent },
      { path: 'update-product/:publicId', component: UpdateProductInventaryComponent },
      { path: '', redirectTo: 'inventary', pathMatch: 'full' }
    ]
  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
