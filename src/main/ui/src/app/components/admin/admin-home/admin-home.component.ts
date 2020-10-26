import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.sass']
})
export class AdminHomeComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  gotoSeriesList(): void {
    this.router.navigate(['/admin/series']);
  }

  gotoSeasonList(): void {
    this.router.navigate(['/admin/season']);
  }

  gotoEpisodeList(): void {
    this.router.navigate(['/admin/episode']);
  }

  gotoUserList(): void {
    // this.router.navigate(['/admin/user']);
  }

}
