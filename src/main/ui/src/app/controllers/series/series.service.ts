import { Injectable } from '@angular/core';
import {Series} from '../../models/series/series';

@Injectable({
  providedIn: 'root'
})
export class SeriesService {

  series = [
    new Series(0, 'MySeries1'),
    new Series(1, 'MySeries2'),
  ];

  constructor() { }

  // This function return information about the series passed in param
  public getSeriesById(id: number): Series {
    return this.series[id];
  }

  // This function return the list of series
  public getAllSeries(): Series[] {
    return this.series;
  }

  // This function create a new Series
  public addSeries(id: number, name: string): void{
    const newSeries = new Series(id, name);
    this.series.push(newSeries);
  }

  // This function remove an entry from Series object
  public removeSeries(id: number): void {
    this.series.splice(id);
  }
}
