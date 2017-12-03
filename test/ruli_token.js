import { RuliToken } from './helpers/ruli_helper';

contract('RuliToken', (accounts) => {

  describe('CONTRACT DEPLOYMENT', () => {
    let token;

    beforeEach(async () => {
      token = await RuliToken.new();
    });
    describe('initialized correctly', () => {
      it('should be correct token name', async function () {
        const expect = 'RuliToken';
        const actual = await this.token.name();
        actual.should.be.equal(expect);
      });

      it('should be correct token symbol', async function () {
        const expect = 'RULI';
        const actual = await this.token.symbol();
        actual.should.be.equal(expect);
      });

      it('should be correct token decimals', async function () {
        const expect = 18;
        const actual = await this.token.decimals();
        actual.toNumber().should.be.equal(expect);
      });

      it('should be same decimals of ether', async function () {
        const expect = web3.toWei(1, 'ether');
        const tokenDecimals = await this.token.decimals();
        const actual = new web3.BigNumber(1 * Math.pow(10, tokenDecimals))
      });
    });

    it('should start with a totalSupply of 0 when deployed alone', async () => {
      const totalSupply = await token.totalSupply();
      assert.equal(totalSupply, 0);
    });
  });

  describe('functions', () => {

    it('should return mintingFinished false after construction', async () => {
      const mintingFinished = await token.mintingFinished();

      assert.equal(mintingFinished, false);
    });

    it('should mint a given amount of tokens a given address', async () => {
      await token.mint(accounts[0], 100);

      const balance0 = await token.balanceOf(accounts[0]);
      assert(balance0, 100);

      const totalSupply = await token.totalSupply();
      assert(totalSupply, 100);
    });
  });
});
