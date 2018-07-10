const fs = require('fs');

const RuliCrowdsale = artifacts.require('RuliCrowdsale.sol');
const crowdsaleParams = JSON.parse(fs.readFileSync('../config/Crowdsale.json', 'utf8'));
const rate = crowdsaleParams.rate;

function ruli(n) {
  return new web3.BigNumber(web3.toWei(n, 'ether'));
}

module.exports = function deployContracts(deployer) {
  const actualCap = ruli(crowdsaleParams.cap);
  const actualInitialRuliFundBalance = ruli(crowdsaleParams.initialRuliFundBalance);

  deployer.deploy(
    RuliCrowdsale, {gas: 155000}, crowdsaleParams.startBlock, crowdsaleParams.endBlock,
    rate.base, crowdsaleParams.ruliFundAddress, actualCap,
    actualInitialRuliFundBalance, crowdsaleParams.goal,
    rate.preSale, rate.week1, rate.week2, rate.week3
  );
};
