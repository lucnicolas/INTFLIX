import { Injectable } from '@angular/core';
import {Season} from '../../model/season/season';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SeasonsService {

  private readonly seasonUrl: string;

  constructor(private http: HttpClient) {
    this.seasonUrl = 'http://localhost:8080/api/season';
  }

  public findAll(): Observable<Season[]> {
    return this.http.get<Season[]>(this.seasonUrl);
  }

  public save(season: Season) {
    return this.http.post<Season>(this.seasonUrl, season);
  }

  public delete(season: Season) {
    return this.http.delete<Season>(this.seasonUrl + "?id=" + season.id);
  }
}
