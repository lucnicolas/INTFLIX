import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {SeriesService} from "../../../service/series/series.service";
import {Series} from "../../../model/series/series";

@Component({
  selector: 'app-series-list',
  templateUrl: './series-list.component.html',
  styleUrls: ['./series-list.component.sass']
})
export class SeriesListComponent implements OnInit {

  series: Series[];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private seriesService: SeriesService) { }

  ngOnInit(): void {
    this.getAllSeries();
  }

  getAllSeries() {
    this.seriesService.findAll().subscribe(data => {
      this.series = data;
    });
  }

  deleteSeries(series: Series) {
    this.seriesService.delete(series).subscribe(() => this.getAllSeries());
  }

  gotoSeriesForm() {
    this.router.navigate(['/admin/series/add']);
  }

}
