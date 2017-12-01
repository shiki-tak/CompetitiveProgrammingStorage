const fs = require('fs');

const RuliCrowdsale = artifacts.require('RuliCrowdsale.sol');
const crowdsaleParams = JSON.parse(fs.readFileSync('../config/Crowdsale.json', 'utf8'));

function ruli(n) {
  return new web3.BigNumber(n).mul(1000000000000000000);
}

module.exports = function deployContracts(deployer) {
  const actualCap = ruli(crowdsaleParams.cap);
  const actualInitialRuliFundBalance = ruli(crowdsaleParams.initialRuliFundBalance);

  deployer.deploy(RuliCrowdsale, crowdsaleParams.startBlock, crowdsaleParams.endBlock, crowdsaleParams.rate, crowdsaleParams.ruliFundAddress, actualCap, actualInitialRuliFundBalance);
};
