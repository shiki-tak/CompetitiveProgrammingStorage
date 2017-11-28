const fs = require('fs');
const RuliToken = artifacts.require('RuliToken.sol');
const initParams = JSON.parse(fs.readFileSync('../config/RuliToken.json', 'utf8'));

module.exports = function(deployer) {
  deployer.deploy(RuliToken, initParams.amount, initParams.name, initParams.decimal, initParams.symbol);
};
