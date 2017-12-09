const fs = require('fs');

const HelloWorld = artifacts.require('HelloWorld.sol');
module.exports = function deployContracts(deployer) {
  deployer.deploy(HelloWorld, 'Hello, World');
/*
const oreoreCoinParams = JSON.parse(fs.readFileSync('../config/OreOreCoin.json', 'utf8'));
const OreOreCoin = artifacts.require('OreOreCoin.sol');
module.exports = function deployContracts(deployer) {
  deployer.deploy(OreOreCoin, oreoreCoinParams.supply, oreoreCoinParams.name, oreoreCoinParams.symbol, oreoreCoinParams.decimals);
}
*/
}
