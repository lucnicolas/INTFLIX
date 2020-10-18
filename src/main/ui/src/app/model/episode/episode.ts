import {Season} from '../season/season';

export class Episode {
  id: number;
  fkSeason: Season;
  title: string;
  num: number;
  seen: boolean;
}
