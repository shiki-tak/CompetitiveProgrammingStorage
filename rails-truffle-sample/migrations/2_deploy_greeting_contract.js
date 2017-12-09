const fs = require('fs');

const HelloWorld = artifacts.require('GreetingContract.sol');
module.exports = function deployContracts(deployer) {
  deployer.deploy(HelloWorld, 'Hello, World');
}
