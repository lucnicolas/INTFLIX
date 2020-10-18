import { Component, OnInit } from '@angular/core';
import {Series} from "../../model/series/series";
import {ActivatedRoute, Router} from "@angular/router";
import {SeriesService} from "../../service/series/series.service";

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
    this.router.navigate(['/series/add']);
  }

}
