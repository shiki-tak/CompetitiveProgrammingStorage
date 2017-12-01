import ruli from '../../utilities/ruli';

const fs = require('fs');
const chai = require('chai');
const chaiAsPromised = require('chai-as-promised');
const chaiBigNumber = require('chai-bignumber');


const crowdsaleParams = JSON.parse(fs.readFileSync('./config/Crowdsale.json', 'utf8'));

export const BigNumber = web3.BigNumber;
export const should = require('chai')
  .use(require('chai-as-promised'))
  .use(require('chai-bignumber')(BigNumber))
  .should();

  export const RuliToken = artifacts.require('RuliToken.sol');
  export const RuliCrowdsale = artifacts.require('RuliCrowdsale.sol');
  export const cap = ruli(crowdsaleParams.cap);
  export const rate = new BigNumber(crowdsaleParams.rate);
  export const ruliFundAddress = crowdsaleParams.ruliFundAddress;
  export const initialRuliFundBalance = ruli(crowdsaleParams.initialRuliFundBalance);
