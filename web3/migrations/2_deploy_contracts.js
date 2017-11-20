const fs = require('fs');

const HelloWorld = artifacts.require('HelloWorld.sol');

module.exports = function deployContracts(deployer) {
  deployer.deploy(HelloWorld);
}
