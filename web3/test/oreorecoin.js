import { OreOreCoin } from './helpers/oreorecoin_helper';

contract('OreOreCoin', (accounts) => {
  let oreorecoin;

  // それぞれのテストを実施する前にOreOreCoinのインスタンスを生成する
  beforeEach(async () => {
    oreorecoin = await OreOreCoin.new();
  });

  describe('initialized correctly', () => {
    it('should be correct coin name', async () => {
      const expect = 'OreOreCoin';
      const actual = await oreorecoin.name();
      actual.should.be.equal(expect);
    });
  });
});
