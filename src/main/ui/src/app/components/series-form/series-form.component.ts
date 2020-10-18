import { Component, OnInit } from '@angular/core';
import {Series} from "../../model/series/series";
import {ActivatedRoute, Router} from "@angular/router";
import {SeriesService} from "../../service/series/series.service";

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

  onSubmit() {
    this.seriesService.save(this.series).subscribe(() => this.gotoSeriesList());
  }

  gotoSeriesList() {
    this.router.navigate(['/series']);
  }
}
