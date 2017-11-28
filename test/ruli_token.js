const RuliToken = artifacts.require('RuliToken.sol');

contract('RuliToken', (accounts) => {
  it('should put 300,000,000 RuliToken in the first account', () => RuliToken.deployed().then(instance => (
    instance.balanceOf.call(accounts[0])
  )).then((balance) => {
    assert.equal(balance.valueOf(), 300000000, `wrong token amount: ${balance.valueOf()}`);
  }));
});
