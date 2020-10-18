import {Season} from '../season/season';

export class Episode {
  constructor(
    public id: number,
    public fkSeason: Season,
    public title: string,
    public num: number,
    public seen?: boolean,
  ) { }
}
