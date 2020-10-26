import { Component, OnInit } from '@angular/core';
import {Episode} from "../../../model/episode/episode";
import {ActivatedRoute, Router} from "@angular/router";
import {EpisodesService} from "../../../service/episodes/episodes.service";

@Component({
  selector: 'app-episode-list',
  templateUrl: './episode-list.component.html',
  styleUrls: ['./episode-list.component.sass']
})
export class EpisodeListComponent implements OnInit {

  episodes: Episode[];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private episodesService: EpisodesService) { }

  ngOnInit(): void {
    this.getAllEpisodes();
  }

  getAllEpisodes() {
    this.episodesService.findAll().subscribe(data => {
      this.episodes = data;
    });
  }

  deleteSeason(episode: Episode) {
    this.episodesService.delete(episode).subscribe( () => this.getAllEpisodes());
  }

  gotoEpisodeForm() {
    this.router.navigate(['/admin/episode/add']);
  }

}
