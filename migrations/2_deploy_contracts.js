const fs = require('fs');

const RuliToken = artifacts.require('RuliToken.sol');
const initParams = JSON.parse(fs.readFileSync('../config/RuliToken.json', 'utf8'));

module.exports = function deployContracts(deployer) {
  deployer.deploy(
    RuliToken, initParams.initialAmount, initParams.name, initParams.decimal,
    initParams.symbol, initParams.offeredAmount
  );
};
