import { DemoUser } from './user.model';

describe('Demo User', () => {
  it('should create an instance', () => {
    expect(new DemoUser()).toBeTruthy();
  });
});
