require('babel-register');
require('babel-polyfill');

module.exports = {
  networks: {
    development: {
      host: "localhost",
      port: 8545,
      network_id: "*", // Match test network id
      gas: 3000000,
      gasPrice: 21000000000
    },
    testrpc: {
      host: "localhost",
      port: 8545,
      network_id: "*", // Match any network id
      gas: 3000000,
      gasPrice: 21000000000
    }
  }
};
