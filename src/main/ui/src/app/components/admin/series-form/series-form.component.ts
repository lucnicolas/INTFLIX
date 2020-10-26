import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {SeriesService} from '../../../service/series/series.service';
import {Series} from '../../../model/series/series';

@Component({
  selector: 'app-series-form',
  templateUrl: './series-form.component.html',
  styleUrls: ['./series-form.component.sass']
})
export class SeriesFormComponent implements OnInit {

  series: Series;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private seriesService: SeriesService) {
  }

  ngOnInit(): void {
    this.series = new Series();
  }

  onSubmit(): void {
    this.seriesService.save(this.series).subscribe(() => this.gotoSeriesList());
  }

  gotoSeriesList(): void {
    this.router.navigate(['/admin/series']);
  }
}
