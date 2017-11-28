let RuliToken = artifacts.require('RuliToken.sol');
let RuliCrowdsale = artifacts.require('RuliCrowdsale.sol');

contract('Crowdsale', () => {
  let crowdSale;

  describe('CONTRACT DEPLOYMENT', () => {
    it('should has deployed address of RuliToken', () => RuliCrowdsale.deployed().then(
      instance => {
        crowdSale = instance;
        console.log(RuliToken.address);

        return crowdsale.token().then(
          tokenAddress => {
            assert.equal(RuliToken.address, tokenAddress, `wrong token address: ${tokenAddress}`);
          }
        )
      }
    ));
    it('should has specified address of RuliFund', () => crowdsale.fund().then(
      fund => {
        assert.equal(fund, '0xd4232bdbc4cdbdc9d131103de55b9a2b68d45c78', `wrong token address: ${fund}`);
      }
    ));
    it('should has offered RULI Toke amount 150,000,000', () => crowdsale.offeredAmount().then(
      offeredAmount => {
        assert.equal(offeredAmount, 150000000, `wrong amount: ${offeredAmount}`);
      }
    ));
    it('should has exchange rate 2,800 of ETH to RULi', () => crowdsale.rate().then(
      rate => {
        assert.equal(rate, 2800, `wrong rate: ${rate}`);
      }
    ));
  });
});
