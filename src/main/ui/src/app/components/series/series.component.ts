import {Component, OnInit} from '@angular/core';
import {Series} from '../../model/series/series';
import {SeriesService} from '../../service/series/series.service';
import {EpisodesService} from '../../service/episodes/episodes.service';

@Component({
  selector: 'app-series',
  templateUrl: './series.component.html',
  styleUrls: ['./series.component.sass']
})
export class SeriesComponent implements OnInit {
  public series: Series[];

  constructor(seriesService: SeriesService, private episodesService: EpisodesService) {
    this.series = seriesService.getAllSeries();
  }

  ngOnInit(): void { }

  public getNbSeasons(seriesId: number): number {
    return this.series.length;
  }

  public getNbEpisodes(seriesId: number): number {
    return this.episodesService.getAllEpisodes().length;
  }

}
