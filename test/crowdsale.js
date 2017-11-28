const Crowdsale = artifacts.require('Crowdsale.sol');

contract('Crowdsale', (accounts) => {
  let crowdSale;

  describe('CONTRACT DEPLOYMENT', () => {
    it('should FIX', () => Crowdsale.deployed().then(
      instance => {
        crowdSale = instance;
        console.log('pending.');
        return true;
      }
    ));
  });
});
