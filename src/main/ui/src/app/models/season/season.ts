import {Series} from '../series/series';

export class Season {
  constructor(
    public id: number,
    public fkSeries: Series,
    public num: number,
  ) { }
}
