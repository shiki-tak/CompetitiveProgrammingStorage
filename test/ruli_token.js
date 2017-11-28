const RuliToken = artifacts.require('RuliToken.sol');

contract('RuliToken', (accounts) => {
  let ruliToken;

  describe('CONTRACT DEPLOYMENT', () => {
    it('should put 300,000,000 RuliToken in the first account', () => RuliToken.deployed().then(
      instance => {
        ruliToken = instance;
        return instance.balanceOf.call(accounts[0]);
      }
    ).then(
      balance => {
        assert.equal(balance.valueOf(), 300000000, `wrong token amount: ${balance.valueOf()}`);
      })
    );
    it('should put 150,000,000 amount in the offeredAmount property.', () => ruliToken.offeredAmount().then(
      amount => (
        assert.equal(amount.valueOf(), 150000000)
      ))
    );
  });
});
