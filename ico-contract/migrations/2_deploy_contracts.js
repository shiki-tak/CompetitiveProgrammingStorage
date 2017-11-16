const fs = require('fs');

const TakFund = artifacts.require('TakFund.sol');
const TakCrowdsale = artifacts.require('TakCrowdsale.sol');
const fundParams = JSON.parse(fs.readFileSync('../config/TakFund.json', 'utf8'));
const crowdsaleParams = JSON.parse(fs.readFileSync('../config/Crowdsale.json', 'utf8'));
