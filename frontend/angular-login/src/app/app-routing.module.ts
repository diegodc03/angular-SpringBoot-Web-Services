import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './authentication/pages/login/login.component';
import { ReactiveFormsComponent } from './authentication/pages/reactive-forms/reactive-forms.component';
import { IndexComponent } from './authentication/pages/index/index.component';
import { DashboardComponent } from './authentication/pages/dashboard/dashboard.component';
import { PrivateZoneComponent } from './authentication/pages/private-zone/private-zone.component';
import { PublicLayoutComponent } from './layouts/public-layout/public-layout.component';
import { SecureLayoutComponent } from './layouts/secure-layout/secure-layout.component';
import { AuthGuard } from './authentication/service/guard/guard.component';
import { ChangePasswordComponent } from './authentication/pages/password-change/password-change.component';
import { SecureInventarySaleLayoutComponent } from './layouts/secure-inventary-sale-layout/secure-inventary-sale-layout.component';
import { InventaryComponent } from './inventarySale/pages/inventary/inventary/inventary.component';
import { AddProductInventaryComponent } from './inventarySale/pages/inventary/add-product-inventary/add-product-inventary.component';
import { UpdateProductInventaryComponent } from './inventarySale/pages/inventary/update-product-inventary/update-product-inventary.component';
import { SaleComponent } from './inventarySale/pages/sale/sale/sale.component';
import { ShowProductInventaryComponent } from './inventarySale/pages/inventary/show-product-inventary/show-product-inventary.component';
import { ShoppingBasketComponent } from './inventarySale/pages/sale/shopping-basket/shopping-basket.component';
import { ShowProductSizes } from './inventarySale/pages/sale/show-product-sizes/show-product-sizes.component';
import { HistorySalesViewComponent } from './inventarySale/pages/sale/history-sales-view/history-sales-view.component';
import { ShowSaleInformationComponent } from './inventarySale/pages/sale/show-sale-information/show-sale-information.component';
import { ShowSeasonMatchesComponent } from './workHours/pages/show-season-matches/show-season-matches.component';
import { ShowSeasonEarningsComponent } from './workHours/pages/show-season-earnings/show-season-earnings.component';
import { SecureWorkHoursLayoutComponent } from './layouts/secure-work-hours-layout/secure-work-hours-layout.component';
import { AddMatchesComponent } from './workHours/pages/add-matches/add-matches.component';
import { AddSeasonComponent } from './workHours/pages/add-season/add-season.component';
import { ShowLeaguesComponent } from './sport/pages/show-leagues/show-leagues.component';
import { ShowClasificationComponent } from './sport/pages/show-clasification/show-clasification.component';
import { ShowLeaguesJoinedComponent } from './sport/pages/show-leagues-joined/show-leagues-joined.component';
import { CreateMatchComponent } from './sport/pages/create-match/create-match.component';
import { CreateLeagueComponent } from './sport/pages/create-league/create-league.component';

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
      { path: 'sales', component: SaleComponent },
      { path: '', redirectTo: 'inventary', pathMatch: 'full' },
      { path: 'show-product/:publicId', component: ShowProductInventaryComponent },
      { path: 'shopping-basket', component: ShoppingBasketComponent },
      { path: 'show-product-sizes', component: ShowProductSizes },
      { path: 'history-sales-view', component: HistorySalesViewComponent }, 
      { path: 'show-sale-information', component: ShowSaleInformationComponent },

    ]
  },
  {
    path: 'work-hours',
    component: SecureWorkHoursLayoutComponent,
    canActivate: [AuthGuard],
    children: [
      { path: 'matchs-working-hours', component: ShowSeasonMatchesComponent },
      { path: 'show-work-earnings', component: ShowSeasonEarningsComponent },
      { path: 'add-matches', component: AddMatchesComponent },
      { path: 'add-season', component: AddSeasonComponent },
      { path: '', redirectTo: 'matchs-working-hours', pathMatch: 'full' }
    ]
  },
  {
    path: 'sports',
    component: SecureWorkHoursLayoutComponent,
    canActivate: [AuthGuard],
    children: [
      { path: 'league-to-join', component: ShowLeaguesComponent },
      { path: 'league-joined', component: ShowLeaguesJoinedComponent },
      { path: 'clasification', component: ShowClasificationComponent },
      { path: 'create-match', component: CreateMatchComponent },
      { path: 'create-league', component: CreateLeagueComponent },
      { path: '', redirectTo: 'matchs-working-hours', pathMatch: 'full' }
    ]
  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
