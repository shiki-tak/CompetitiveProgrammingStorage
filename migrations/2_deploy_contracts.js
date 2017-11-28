const fs = require('fs');

const RuliToken = artifacts.require('RuliToken.sol');
const RuliCrowdsale = artifacts.require('RuliCrowdsale.sol');
const tokenParams = JSON.parse(fs.readFileSync('../config/RuliToken.json', 'utf8'));
const crowdsaleParams = JSON.parse(fs.readFileSync('../config/Crowdsale.json', 'utf8'));

module.exports = function deployContracts(deployer) {

  return deployer.deploy(
    RuliToken, tokenParams.initialAmount, tokenParams.name, tokenParams.decimal,
    tokenParams.symbol).then(() =>
  deployer.deploy(RuliCrowdsale, RuliToken.address, crowdsaleParams.fundAddress, crowdsaleParams.offeredAmount, crowdsaleParams.initialRate));
};
