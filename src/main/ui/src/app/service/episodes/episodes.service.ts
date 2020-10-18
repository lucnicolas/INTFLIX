import { Injectable } from '@angular/core';
import {Episode} from '../../model/episode/episode';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class EpisodesService {

  private readonly episodeUrl: string;

  constructor(private http: HttpClient) {
    this.episodeUrl = 'http://localhost:8080/api/episode';
  }

  public findAll(): Observable<Episode[]> {
    return this.http.get<Episode[]>(this.episodeUrl);
  }

  public save(episode: Episode) {
    return this.http.post<Episode>(this.episodeUrl, episode);
  }

  public delete(episode: Episode) {
    return this.http.delete<Episode>(this.episodeUrl + "?id=" + episode.id);
  }
}
