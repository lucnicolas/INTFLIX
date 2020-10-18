import { Injectable } from '@angular/core';
import {Season} from '../../models/season/season';
import {SeriesService} from '../series/series.service';
import {Series} from '../../models/series/series';

@Injectable({
  providedIn: 'root'
})
export class SeasonsService {
  series: Series[];
  seasons: Season[];

  constructor(seriesService: SeriesService) {
    this.series = seriesService.getAllSeries();
    this.seasons = [
      new Season(0, this.series[0], 1),
      new Season(1, this.series[0], 2),
      new Season(2, this.series[1], 1),
      new Season(3, this.series[1], 2),
    ];
  }

  // This function return information about the seasons passed in param
  public getSeasonById(id: number): Season {
    return this.seasons[id];
  }

  // This function return the list of season
  public getAllSeasons(): Season[] {
    return this.seasons;
  }

  getSeasonsBySeries(seriesId: number): Season[] {
    return this.seasons;
  }

  // This function create a new season
  public addSeason(id: number, fkSeries: Series, num: number): void{
    const newSeason = new Season(id, fkSeries, num);
    this.seasons.push(newSeason);
  }

  // This function remove an entry from Season object
  public removeSeason(id: number): void {
    this.seasons.splice(id);
  }
}
