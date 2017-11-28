const fs = require('fs');

const RuliToken = artifacts.require('RuliToken.sol');
const Crowdsale = artifacts.require('Crowdsale.sol');
const initParams = JSON.parse(fs.readFileSync('../config/RuliToken.json', 'utf8'));

module.exports = function deployContracts(deployer) {

  const fund = '0x0000000000000000000000000000000000000000';

  return deployer.deploy(
    RuliToken, initParams.initialAmount, initParams.name, initParams.decimal,
    initParams.symbol, initParams.offeredAmount).then(() =>
  deployer.deploy(Crowdsale, RuliToken.address, fund));
};
