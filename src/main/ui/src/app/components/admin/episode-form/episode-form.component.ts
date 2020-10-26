import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Episode} from "../../../model/episode/episode";
import {EpisodesService} from "../../../service/episodes/episodes.service";

@Component({
  selector: 'app-episode-form',
  templateUrl: './episode-form.component.html',
  styleUrls: ['./episode-form.component.sass']
})
export class EpisodeFormComponent implements OnInit {

  episode: Episode;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private episodesService: EpisodesService) {
  }

  ngOnInit(): void {
    this.episode = new Episode();
  }

  onSubmit() {
    this.episodesService.save(this.episode).subscribe(() => this.gotoEpisodeList());
  }

  gotoEpisodeList() {
    this.router.navigate(['/admin/episode']);
  }
}
