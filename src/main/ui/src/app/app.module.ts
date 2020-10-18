import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import { Routes, RouterModule } from '@angular/router'; // CLI imports router

import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { AuthService } from './auth/auth.service';
import { SeriesListComponent } from './components/series-list/series-list.component';
import { SeriesFormComponent } from './components/series-form/series-form.component';
import {AppRoutingModule} from "./app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";

// configures NgModule imports and exports
@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    SeriesListComponent,
    SeriesFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  exports: [RouterModule],
  providers: [AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
