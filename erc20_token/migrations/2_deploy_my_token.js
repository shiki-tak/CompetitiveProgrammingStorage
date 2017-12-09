const MyToken = artifacts.require('./MyToken.sol');

module.exports = (deployer) => {
  let initialSupply = 5000e18;
  deployer.deploy(MyToken, initialSupply);
}
