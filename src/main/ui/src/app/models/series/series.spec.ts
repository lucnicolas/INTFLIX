import {Series} from './series';

describe('Series', () => {
  it('should create an instance', () => {
    expect(new Series(1, 'MySeries')).toBeTruthy();
  });
});
