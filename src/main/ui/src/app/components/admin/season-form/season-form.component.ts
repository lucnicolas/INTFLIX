import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Season} from '../../../model/season/season';
import {SeasonsService} from '../../../service/seasons/seasons.service';
import {SeriesService} from '../../../service/series/series.service';
import {Series} from '../../../model/series/series';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-season-form',
  templateUrl: './season-form.component.html',
  styleUrls: ['./season-form.component.sass']
})
export class SeasonFormComponent implements OnInit {

  season: Season;
  seriesList: Observable<Series[]>;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private seasonsService: SeasonsService,
    private seriesService: SeriesService) {
  }

  ngOnInit(): void {
    this.season = new Season();
    this.seriesList = this.seriesService.findAll();
  }

  onSubmit(): void {
    this.seasonsService.save(this.season).subscribe(() => this.gotoSeasonList());
  }

  gotoSeasonList(): void {
    this.router.navigate(['/admin/season']);
  }
}
