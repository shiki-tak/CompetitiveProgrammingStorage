const fs = require('fs');

const RuliToken = artifacts.require('RuliToken.sol');
const RuliCrowdsale = artifacts.require('RuliCrowdsale.sol');
// const tokenParams = JSON.parse(fs.readFileSync('../config/RuliToken.json', 'utf8'));
const crowdsaleParams = JSON.parse(fs.readFileSync('../config/Crowdsale.json', 'utf8'));

module.exports = function deployContracts(deployer) {

  deployer.deploy(RuliToken);

  deployer.link(RuliToken, RuliCrowdsale);

  // TODO: start & end block.
  deployer.deploy(RuliCrowdsale, 900000, 1000000, crowdsaleParams.initialRate, crowdsaleParams.fundAddress);
};
