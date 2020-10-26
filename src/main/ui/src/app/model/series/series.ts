import {Season} from '../season/season';

export class Series {
  id: number;
  name: string;
  seen: boolean;
  fkSeries: Season;
}
