import { Component, OnInit } from '@angular/core';
import {Season} from "../../../model/season/season";
import {ActivatedRoute, Router} from "@angular/router";
import {SeasonsService} from "../../../service/seasons/seasons.service";

@Component({
  selector: 'app-season-list',
  templateUrl: './season-list.component.html',
  styleUrls: ['./season-list.component.sass']
})
export class SeasonListComponent implements OnInit {

  season: Season[];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private seasonsService: SeasonsService) { }

  ngOnInit(): void {
    this.getAllSeasons();
  }

  getAllSeasons() {
    this.seasonsService.findAll().subscribe(data => {
      this.season = data;
    });
  }

  deleteSeason(season: Season) {
    this.seasonsService.delete(season).subscribe( () => this.getAllSeasons());
  }

  gotoSeasonForm() {
    this.router.navigate(['/admin/season/add']);
  }

}
