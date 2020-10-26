import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import { RouterModule } from '@angular/router'; // CLI imports router

import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { AuthService } from './auth/auth.service';
import { SeriesListComponent } from './components/admin/series-list/series-list.component';
import { SeriesFormComponent } from './components/admin/series-form/series-form.component';
import {AppRoutingModule} from './app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import { SeasonListComponent } from './components/admin/season-list/season-list.component';
import { SeasonFormComponent } from './components/admin/season-form/season-form.component';
import { EpisodeListComponent } from './components/admin/episode-list/episode-list.component';
import { EpisodeFormComponent } from './components/admin/episode-form/episode-form.component';
import { AdminHomeComponent } from './components/admin/admin-home/admin-home.component';

// configures NgModule imports and exports
@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    SeriesListComponent,
    SeriesFormComponent,
    SeasonListComponent,
    SeasonFormComponent,
    EpisodeListComponent,
    EpisodeFormComponent,
    AdminHomeComponent,
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
