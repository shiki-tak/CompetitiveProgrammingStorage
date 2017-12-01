const fs = require('fs');

const RuliToken = artifacts.require('RuliToken.sol');
const RuliCrowdsale = artifacts.require('RuliCrowdsale.sol');
const crowdsaleParams = JSON.parse(fs.readFileSync('../config/Crowdsale.json', 'utf8'));

function ruli(n) {
  return new web3.BigNumber(n).mul(1000000000000000000);
}

module.exports = function deployContracts(deployer) {
  const actualCap = ruli(crowdsaleParams.cap);
  const actualInitialRuliFundBalance = ruli(crowdsaleParams.initialRuliFundBalance);

  // TODO: start & end block.
  deployer.deploy(RuliCrowdsale, 900000, 1000000, crowdsaleParams.rate, crowdsaleParams.ruliFundAddress, actualCap, actualInitialRuliFundBalance);
};
