import {Series} from '../series/series';

export class Season {
  id: number;
  fkSeries: Series;
  num: number;
  seen: boolean
}
