import {Component, OnInit} from '@angular/core';
import {Season} from '../../models/season/season';
import {SeasonsService} from '../../controllers/seasons/seasons.service';
import {EpisodesService} from '../../controllers/episodes/episodes.service';
import {ActivatedRoute} from '@angular/router';
import {Episode} from '../../models/episode/episode';
import {Series} from '../../models/series/series';

@Component({
  selector: 'app-seasons',
  templateUrl: './seasons.component.html',
  styleUrls: ['./seasons.component.sass']
})
export class SeasonsComponent implements OnInit {
  public seasons: Season[];
  public episodes: Episode[];
  public currentSeries: string;
  private seasonsService: SeasonsService;
  private episodesService: EpisodesService;

  constructor(seasonsService: SeasonsService, episodesService: EpisodesService, private route: ActivatedRoute) {
    this.seasonsService = seasonsService;
    this.episodesService = episodesService;
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if (params.seriesName && params.seriesId) {
        this.seasons = this.seasonsService.getSeasonsBySeries(params.seriesId);
        this.currentSeries = params.seriesName;
        for (let season of this.seasons) {
          this.episodes = this.episodesService.getEpisodesBySeason(season.id);
        }
      }
    });
  }
}
