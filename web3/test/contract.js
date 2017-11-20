var Web3 = require('web3');
var web3 = new Web3();

web3.setProvider(new web3.providers.HttpProvider('http://localhost:8545'));

var address = '0x7262f4e71a4385078166e0f5df02fd562d588fc8';

var abi = [{"constant": true,"inputs": [],"name": "say","outputs": [{"name": "","type": "string"}],"payable": false,"stateMutability": "view","type": "function"},{"constant": false,"inputs": [{"name": "_greeting","type": "string"}],"name": "setGreeting","outputs": [{"name": "","type": "string"}],"payable": false,"stateMutability": "nonpayable","type": "function"},{"constant": true,"inputs": [],"name": "greeting","outputs": [{"name": "","type": "string"}],"payable": false,"stateMutability": "view","type": "function"},{"inputs": [{"name": "_greeting","type": "string"}],"payable": false,"stateMutability": "nonpayable","type": "constructor"}];

var contract = web3.eth.contract(abi).at(address);

// 初期値でHelloを実行
var response = contract.say.call();
console.log("Initial value");
console.log("response: ", response);

// setGreetで値を変更してHelloを実行
var greeting = 'Hello, Ethereum!';
var setGreeting = contract.setGreeting.call(greeting);

var ether = contract.say.call();
console.log("Second value");
console.log("response: ", ether);
