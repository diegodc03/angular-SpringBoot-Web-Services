import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './shared/header/header.component';
import { FooterComponent } from './shared/footer/footer.component';
import { DashboardComponent } from './authentication/pages/dashboard/dashboard.component';
import { LoginComponent } from './authentication/pages/login/login.component';
import { NavComponent } from './shared/nav/nav.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';



import { HttpClient, HttpClientModule } from '@angular/common/http';
import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { PersonalDetailsComponent } from './authentication/pages/personal-details/personal-details.component';
import { JwtInterceptorService } from './interceptor/jwt-interceptor.service';
import { ErrorInterceptorService } from './interceptor/error-interceptor.service';

import { ReactiveFormsComponent } from './authentication/pages/reactive-forms/reactive-forms.component';
import { IndexComponent } from './authentication/pages/index/index.component';
import { PrivateZoneComponent } from './authentication/pages/private-zone/private-zone.component';
import { PublicLayoutComponent } from './layouts/public-layout/public-layout.component';
import { SecureLayoutComponent } from './layouts/secure-layout/secure-layout.component';
import { SecureHeaderComponent } from './shared/secure-header/secure-header.component';
import { SecureNavComponent } from './shared/secure-nav/secure-nav.component';

import { ChangePasswordComponent } from './authentication/pages/password-change/password-change.component';
import { InventaryComponent } from './inventarySale/pages/inventary/inventary/inventary.component';
import { SecureInventarySaleNavComponent } from './shared/secure-inventary-sale-nav/secure-inventary-sale-nav.component';
import { SecureInventarySaleLayoutComponent } from './layouts/secure-inventary-sale-layout/secure-inventary-sale-layout.component';
import { AddProductInventaryComponent } from './inventarySale/pages/inventary/add-product-inventary/add-product-inventary.component';
import { UpdateProductInventaryComponent } from './inventarySale/pages/inventary/update-product-inventary/update-product-inventary.component';
import { SaleComponent } from './inventarySale/pages/sale/sale/sale.component';
import { ShowProductInventaryComponent } from './inventarySale/pages/inventary/show-product-inventary/show-product-inventary.component';
import { ShoppingBasketComponent } from './inventarySale/pages/sale/shopping-basket/shopping-basket.component';
import { ShowProductSizes } from './inventarySale/pages/sale/show-product-sizes/show-product-sizes.component';
import { HistorySalesViewComponent } from './inventarySale/pages/sale/history-sales-view/history-sales-view.component';
import { ShowSaleInformationComponent } from './inventarySale/pages/sale/show-sale-information/show-sale-information.component';
import { JwtInterceptor } from './interceptor/jwt-interceptor';
import { SecureWorkHoursLayoutComponent } from './layouts/secure-work-hours-layout/secure-work-hours-layout.component';
import { SecureWorkHoursNavComponent } from './shared/secure-work-hours-nav/secure-work-hours-nav.component';
import { ShowSeasonEarningsComponent } from './workHours/pages/show-season-earnings/show-season-earnings.component';
import { AddMatchesComponent } from './workHours/pages/add-matches/add-matches.component';
import { AddSeasonComponent } from './workHours/pages/add-season/add-season.component';
import { CommonModule } from '@angular/common';

@NgModule({ declarations: [
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
        ChangePasswordComponent,
        InventaryComponent,
        SecureInventarySaleNavComponent,
        SecureInventarySaleLayoutComponent,
        ShowProductInventaryComponent,
        ShowProductSizes,
        SecureWorkHoursLayoutComponent,
        SecureWorkHoursNavComponent,
        ShowSeasonEarningsComponent,
        AddMatchesComponent,
        AddSeasonComponent,
        ShowSaleInformationComponent,
        HistorySalesViewComponent,
        SaleComponent,
        ShoppingBasketComponent,
        SecureInventarySaleNavComponent,
        SecureInventarySaleLayoutComponent,
        AddProductInventaryComponent,
        UpdateProductInventaryComponent,

        
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        ReactiveFormsModule,
        FormsModule,
        HttpClientModule,
        CommonModule
      ],
      providers: [
        {provide:HTTP_INTERCEPTORS,useClass:JwtInterceptorService,multi:true},
        {provide:HTTP_INTERCEPTORS,useClass:ErrorInterceptorService,multi:true},
        {provide:HTTP_INTERCEPTORS,useClass:JwtInterceptor,multi:true},
      ],
      bootstrap: [AppComponent]
    })
export class AppModule { }
