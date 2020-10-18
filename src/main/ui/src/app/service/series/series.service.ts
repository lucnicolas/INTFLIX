import { Injectable } from '@angular/core';
import {Series} from '../../model/series/series';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SeriesService {

  private readonly seriesUrl: string;

  constructor(private http: HttpClient) {
    this.seriesUrl = 'http://localhost:8080/api/episode';
  }

  public findAll(): Observable<Series[]> {
    return this.http.get<Series[]>(this.seriesUrl);
  }

  public save(series: Series) {
    return this.http.post<Series>(this.seriesUrl, series);
  }

  public delete(series: Series) {
    return this.http.delete<Series>(this.seriesUrl + "?id=" + series.id);
  }
}
