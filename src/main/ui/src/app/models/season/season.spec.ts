import {Season} from './season';
import {SeriesComponent} from '../../components/series/series.component';

describe('Season', () => {
  it('should create an instance', () => {
    expect(new Season(1, new SeriesComponent().getSeriesById(1), 1)).toBeTruthy();
  });
});
