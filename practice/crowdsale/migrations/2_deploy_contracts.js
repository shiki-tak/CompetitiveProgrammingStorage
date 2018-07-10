const fs = require('fs');

const Crowdsale = artifacts.require('Crowdsale.sol');
const TestToken = artifacts.require('TestToken.sol');
const testTokenParams = JSON.parse(fs.readFileSync('../config/TestToken.json', 'utf8'));
const crowdsaleParams = JSON.parse(fs.readFileSync('../config/Crowdsale.json', 'utf8'));

module.exports = function deployContracts(deployer) {
  deployer.deploy(TestToken, testTokenParams.supply, testTokenParams.name, testTokenParams.symbol, testTokenParams.decimales).then(() =>
    deployer.deploy(Crowdsale, crowdsaleParams.fundingGoalInEthers, crowdsaleParams.transferableToken,
      crowdsaleParams.amountOfTokenPerEther, TestToken.address
    ));
};
