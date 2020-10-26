import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SeriesListComponent} from './components/admin/series-list/series-list.component';
import {PageNotFoundComponent} from './components/page-not-found/page-not-found.component';
import {SeriesFormComponent} from './components/admin/series-form/series-form.component';
import {AuthGuard} from './auth/auth.guard';
import {SeasonListComponent} from './components/admin/season-list/season-list.component';
import {SeasonFormComponent} from './components/admin/season-form/season-form.component';
import {EpisodeListComponent} from './components/admin/episode-list/episode-list.component';
import {EpisodeFormComponent} from './components/admin/episode-form/episode-form.component';
import {AdminHomeComponent} from './components/admin/admin-home/admin-home.component';

const routes: Routes = [
  { path: 'admin', component: AdminHomeComponent, canActivate: [AuthGuard]},

  { path: 'admin/series', component: SeriesListComponent, canActivate: [AuthGuard]},
  { path: 'admin/series/add', component: SeriesFormComponent, canActivate: [AuthGuard]},
  { path: 'admin/series/edit/:seriesId', component: SeriesFormComponent, canActivate: [AuthGuard]},
  { path: 'admin/series/delete/:seriesId', component: SeriesFormComponent, canActivate: [AuthGuard]},

  { path: 'admin/season', component: SeasonListComponent, canActivate: [AuthGuard]},
  { path: 'admin/season/add', component: SeasonFormComponent, canActivate: [AuthGuard]},
  { path: 'admin/season/edit/:seasonId', component: SeasonFormComponent, canActivate: [AuthGuard]},
  { path: 'admin/season/delete/:seasonId', component: SeasonFormComponent, canActivate: [AuthGuard]},

  { path: 'admin/episode', component: EpisodeListComponent, canActivate: [AuthGuard]},
  { path: 'admin/episode/add', component: EpisodeFormComponent, canActivate: [AuthGuard]},
  { path: 'admin/episode/edit/:episodeId', component: EpisodeFormComponent, canActivate: [AuthGuard]},
  { path: 'admin/episode/delete/:episodeId', component: EpisodeFormComponent, canActivate: [AuthGuard]},

  // { path: ':seriesName/:seriesId', component: SeasonsComponent},
  // { path: ':seriesName/:episodeTitle/:episodeId', component: EpisodesComponent, canActivate: [AuthGuard]},
  { path: '',   redirectTo: '/admin/series', pathMatch: 'full' }, // redirect to `series`
  { path: '**', component: PageNotFoundComponent },  // Wildcard route for a 404 page
]; // sets up routes constant where you define your routes
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
