import {Component, OnInit} from '@angular/core';
import {Episode} from '../../models/episode/episode';
import {EpisodesService} from '../../controllers/episodes/episodes.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-episodes',
  templateUrl: './episodes.component.html',
  styleUrls: ['./episodes.component.sass']
})
export class EpisodesComponent implements OnInit {
  private episodesService: EpisodesService;
  public episode: Episode;

  constructor(episodesService: EpisodesService, private route: ActivatedRoute) {
    this.episodesService = episodesService;
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if (params.episodeId) {
        this.episode = this.episodesService.getEpisodeById(params.episodeId);
      }
    });
  }

  onclickDelete(episodeId: number): void {
    this.episodesService.removeEpisode(episodeId);
  }

  onClickChangeSeenState(id: number): void {
    this.episodesService.changeSeenState(id);
  }
}
