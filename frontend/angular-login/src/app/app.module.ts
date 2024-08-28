import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './shared/header/header.component';
import { FooterComponent } from './shared/footer/footer.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { LoginComponent } from './auth/login/login.component';
import { NavComponent } from './shared/nav/nav.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { PersonalDetailsComponent } from './components/personal-details/personal-details.component';
import { JwtInterceptorService } from './services/auth/jwt-interceptor.service';
import { ErrorInterceptorService } from './services/auth/error-interceptor.service';


import { ReactiveFormsComponent } from './auth/register/reactive-forms/reactive-forms.component';
import { IndexComponent } from './pages/index/index.component';
import { PrivateZoneComponent } from './pages/private-zone/private-zone.component';
import { PublicLayoutComponent } from './layouts/public-layout/public-layout.component';
import { SecureLayoutComponent } from './layouts/secure-layout/secure-layout.component';
import { SecureHeaderComponent } from './shared/secure-header/secure-header.component';
import { SecureNavComponent } from './shared/secure-nav/secure-nav.component';
import { AuthCheckTokenComponent } from './services/auth-check-token/auth-check-token.component';
import { ChangePasswordComponent } from './pages/password-change/password-change.component';
import { InventaryComponent } from './inventary/inventary.component';
import { SecureInventarySaleNavComponent } from './shared/secure-inventary-sale-nav/secure-inventary-sale-nav.component';
import { SecureInventarySaleLayoutComponent } from './layouts/secure-inventary-sale-layout/secure-inventary-sale-layout.component';
import { AddProductInventaryComponent } from './pages/add-product-inventary/add-product-inventary.component';
import { UpdateProductInventaryComponent } from './pages/update-product-inventary/update-product-inventary.component';
import { SaleComponent } from './sale/sale.component';
import { ShowProductInventaryComponent } from './pages/show-product-inventary/show-product-inventary.component';
import { ShoppingBasketComponent } from './pages/shopping-basket/shopping-basket.component';
import { ShowProductSizes } from './pages/show-product-sizes/show-product-sizes.component';
import { HistorySalesViewComponent } from './pages/history-sales-view/history-sales-view.component';



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    DashboardComponent,
    LoginComponent,
    NavComponent,
    PersonalDetailsComponent,
    ReactiveFormsComponent,
    IndexComponent,
    PrivateZoneComponent,
    PublicLayoutComponent,
    SecureLayoutComponent,
    SecureHeaderComponent,
    SecureNavComponent,
    AuthCheckTokenComponent,
    ChangePasswordComponent,
    InventaryComponent,
    SecureInventarySaleNavComponent,
    SecureInventarySaleLayoutComponent,
    AddProductInventaryComponent,
    UpdateProductInventaryComponent,
    SaleComponent,
    ShowProductInventaryComponent,
    ShoppingBasketComponent,
    ShowProductSizes,
    HistorySalesViewComponent,

    
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [
    {provide:HTTP_INTERCEPTORS,useClass:JwtInterceptorService,multi:true},
    {provide:HTTP_INTERCEPTORS,useClass:ErrorInterceptorService,multi:true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
