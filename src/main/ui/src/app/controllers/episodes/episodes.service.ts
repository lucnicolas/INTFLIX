import { Injectable } from '@angular/core';
import {Season} from '../../models/season/season';
import {Episode} from '../../models/episode/episode';
import {SeasonsComponent} from '../../components/seasons/seasons.component';
import {SeasonsService} from '../seasons/seasons.service';

@Injectable({
  providedIn: 'root'
})
export class EpisodesService {
  seasons: Season[];
  episodes: Episode[];

  constructor(seasonsService: SeasonsService) {
    this.seasons = seasonsService.getAllSeasons();
    this.episodes = [
      new Episode(0, this.seasons[0], 'MyEpisode1', 1, true),
      new Episode(1, this.seasons[0], 'MyEpisode2', 2),
      new Episode(2, this.seasons[1], 'MyEpisode3', 1),
      new Episode(3, this.seasons[1], 'MyEpisode4', 2),
      new Episode(4, this.seasons[2], 'MyEpisode5', 1),
      new Episode(5, this.seasons[2], 'MyEpisode6', 2),
      new Episode(6, this.seasons[3], 'MyEpisode7', 1),
      new Episode(7, this.seasons[3], 'MyEpisode8', 2),
    ];
  }

  // This function return information about the Episode passed in param
  public getEpisodeById(id: number): Episode {
    return this.episodes[id];
  }

  // This function return the list of Episode
  public getAllEpisodes(): Episode[] {
    return this.episodes;
  }

  public getEpisodesBySeason(seasonId: number): Episode[] {
    return this.episodes;
  }

  // This function create a new Episode
  public addEpisode( id: number,
                     fkSeason: Season,
                     title: string,
                     num: number,
                     seen: boolean = false
  ): void {
    const newEpisode = new Episode(id, fkSeason, title, num, seen);
    this.episodes.push(newEpisode);
  }

  // This function remove an entry from Series object
  public removeEpisode(id: number): void {
    this.episodes.splice(id);
  }

  public changeSeenState(id: number): void {
    this.episodes[id].seen = !this.episodes[id].seen;
  }
}
