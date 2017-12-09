var Web3 = require('web3');
var web3 = new Web3();

web3.setProvider(new web3.providers.HttpProvider('http://localhost:8545'));

var address = '0xfc980435077a6b72b9328faf9c0a1fb49f21c2fc';

var abi = [{"constant": true,"inputs": [],"name": "say","outputs": [{"name": "","type": "string"}],"payable": false,"stateMutability": "view","type": "function"},{"constant": false,"inputs": [{"name": "_greeting","type": "string"}],"name": "setGreeting","outputs": [{"name": "","type": "string"}],"payable": false,"stateMutability": "nonpayable","type": "function"},{"constant": true,"inputs": [],"name": "greeting","outputs": [{"name": "","type": "string"}],"payable": false,"stateMutability": "view","type": "function"},{"inputs": [{"name": "_greeting","type": "string"}],"payable": false,"stateMutability": "nonpayable","type": "constructor"}];

var contract = web3.eth.contract(abi).at(address);

greeting = 'Hello, Ethereum!';

var response = contract.say.call();
console.log("Initial value");
console.log(response);

contract.setGreeting(greeting, {from: web3.eth.accounts[0]}, function(err, res) {
  if (err) console.log(err);
  else {
    console.log("set result");
    console.log("response: ", res);
  }
});
