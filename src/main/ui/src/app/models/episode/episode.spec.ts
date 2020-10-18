import {Episode} from './episode';
import {SeasonsComponent} from '../../components/seasons/seasons.component';

describe('Episode', () => {
  it('should create an instance', () => {
    expect(new Episode(1, new SeasonsComponent().getSeasonById(1), 'MyEpisode', 1)).toBeTruthy();
  });
});
