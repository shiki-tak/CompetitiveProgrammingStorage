const fs = require('fs');

const TakFund = artifacts.require('TakFund.sol');
const TakCrowdsale = artifacts.require('TakCrowdsale.sol');
const fundParams = JSON.parse(fs.readFileSync('../config/TakFund.json', 'utf8'));
const crowdsaleParams = JSON.parse(fs.readFileSync('../config/Crowdsale.json', 'utf8'));
const rate = crowdsaleParams.rate;

function tak(n) {
  return new web3.BigNumber(web3.toWei(n, 'ether'));
}

module.exports = function deployContracts(deployer) {
  const actualCap = web3.toWei(crowdsaleParams.cap, 'ether');
  const actualTokenCap = tak(crowdsaleParams.tokenCap);
  const actualInitialTakFundBalance = tak(crowdsaleParams.initialTakFundBalance);
  const actualGoal = web3.toWei(crowdsaleParams.goal, 'ether');

  deployer.deploy(TakFund, fundParams.owners, fundParams.required).then(() =>
    deployer.deploy(TakCrowdsale, crowdsaleParams.startBlock, crowdsaleParams.icoStartTime,
      crowdsaleParams.endBlock, rate.base, TakFund.address, actualCap, actualTokenCap,
      actualInitialTakFundBalance, actualGoal, crowdsaleParams.whiteList
    ));
};
