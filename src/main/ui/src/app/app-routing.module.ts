import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SeriesListComponent} from "./components/series-list/series-list.component";
import {PageNotFoundComponent} from "./components/page-not-found/page-not-found.component";
import {SeriesFormComponent} from "./components/series-form/series-form.component";

const routes: Routes = [
  { path: 'series', component: SeriesListComponent},
  { path: 'series/add', component: SeriesFormComponent},
  //{ path: ':seriesName/:seriesId', component: SeasonsComponent},
  //{ path: ':seriesName/:episodeTitle/:episodeId', component: EpisodesComponent, canActivate: [AuthGuard]},
  { path: '',   redirectTo: '/series', pathMatch: 'full' }, // redirect to `series`
  { path: '**', component: PageNotFoundComponent },  // Wildcard route for a 404 page
]; // sets up routes constant where you define your routes
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
