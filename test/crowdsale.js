const RuliToken = artifacts.require('RuliToken.sol');
const Crowdsale = artifacts.require('Crowdsale.sol');

contract('Crowdsale', () => {
  let crowdSale;

  describe('CONTRACT DEPLOYMENT', () => {
    it('should has deployed address of RuliToken', () => Crowdsale.deployed().then(
      instance => {
        crowdSale = instance;
        return crowdSale.token().then(
          tokenAddress => {
            assert.equal(RuliToken.address, tokenAddress, `wrong token address: ${tokenAddress}`);
          }
        )
      }
    ));
    it('should has specified address of RuliToken', () => crowdSale.fund().then(
      fund => {
        assert.equal(fund, '0x0000000000000000000000000000000000000000', `wrong token address: ${fund}`);
      }
    ));
    it('should has exchange rate 2,800 of ETH to RULi', () => crowdSale.rate().then(
      rate => {
        assert.equal(rate, 2800, `wrong token address: ${rate}`);
      }
    ));
  });
});
