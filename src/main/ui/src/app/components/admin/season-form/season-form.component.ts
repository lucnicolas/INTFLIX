import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Season} from "../../../model/season/season";
import {SeasonsService} from "../../../service/seasons/seasons.service";

@Component({
  selector: 'app-season-form',
  templateUrl: './season-form.component.html',
  styleUrls: ['./season-form.component.sass']
})
export class SeasonFormComponent implements OnInit {

  season: Season;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private seasonsService: SeasonsService) {
  }

  ngOnInit(): void {
    this.season = new Season();
  }

  onSubmit() {
    this.seasonsService.save(this.season).subscribe(() => this.gotoSeasonList());
  }

  gotoSeasonList() {
    this.router.navigate(['/admin/season']);
  }
}
