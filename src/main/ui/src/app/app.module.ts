import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import { Routes, RouterModule } from '@angular/router'; // CLI imports router

import {AppComponent} from './app.component';
import {SeriesComponent} from './components/series/series.component';
import {SeasonsComponent} from './components/seasons/seasons.component';
import {EpisodesComponent} from './components/episodes/episodes.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import {AuthService} from './auth/auth.service';
import {AuthGuard} from './auth/auth.guard';

const routes: Routes = [
  { path: 'home', component: SeriesComponent},
  { path: ':seriesName/:seriesId', component: SeasonsComponent},
  { path: ':seriesName/:episodeTitle/:episodeId', component: EpisodesComponent, canActivate: [AuthGuard]},
  { path: '',   redirectTo: '/home', pathMatch: 'full' }, // redirect to `home`
  { path: '**', component: PageNotFoundComponent },  // Wildcard route for a 404 page
]; // sets up routes constant where you define your routes

// configures NgModule imports and exports
@NgModule({
  declarations: [
    AppComponent,
    SeriesComponent,
    SeasonsComponent,
    EpisodesComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    [RouterModule.forRoot(routes)]
  ],
  exports: [RouterModule],
  providers: [AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
