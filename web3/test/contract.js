var Web3 = require('web3');
var web3 = new Web3();

web3.setProvider(new web3.providers.HttpProvider('http://localhost:8545'));

var address = '0xc02cd84e0498ee1283fe985a5d0dde8b2780794a';

var abi = [{"constant": true,"inputs": [],"name": "Hello","outputs": [{"name": "","type": "string"}],"payable": false,"stateMutability": "view","type": "function"},{"anonymous": false,"inputs": [{"indexed": false,"name": "from","type": "address"},{"indexed": false,"name": "operation","type": "string"}],"name": "ExecHello","type": "event"}]

var contract = web3.eth.contract(abi).at(address);

var response = contract.Hello.call();
console.log("response: ", response);
