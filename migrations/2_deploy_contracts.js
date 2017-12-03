const fs = require('fs');

const RuliCrowdsale = artifacts.require('RuliCrowdsale.sol');
const crowdsaleParams = JSON.parse(fs.readFileSync('../config/Crowdsale.json', 'utf8'));

function ruli(n) {
  return new web3.BigNumber(web3.toWei(n, 'ether'));
}

module.exports = function deployContracts(deployer) {
  const actualCap = ruli(crowdsaleParams.cap);
  const actualInitialRuliFundBalance = ruli(crowdsaleParams.initialRuliFundBalance);

  deployer.deploy(
    RuliCrowdsale, crowdsaleParams.startBlock, crowdsaleParams.endBlock,
    crowdsaleParams.rate, web3.eth.accounts[3], actualCap, actualInitialRuliFundBalance, crowdsaleParams.goal
  );
};
