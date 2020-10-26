import {Episode} from '../episode/episode';

export class Season {
  id: number;
  number: number;
  seen: boolean;
  fkSeason: Episode;
}
