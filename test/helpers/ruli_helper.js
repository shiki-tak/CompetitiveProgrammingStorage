import ruli from '../../utilities/ruli';

const fs = require('fs');
const chai = require('chai');
const chaiAsPromised = require('chai-as-promised');
const chaiBigNumber = require('chai-bignumber');

const crowdsaleParams = JSON.parse(fs.readFileSync('/Users/shikitakahashi/workspace/ico-contracts/config/Crowdsale.json', 'utf8'));

export const BigNumber = web3.BigNumber;
export const should = require('chai')
  .use(require('chai-as-promised'))
  .use(require('chai-bignumber')(BigNumber))
  .should();

export const RuliToken = artifacts.require('RuliToken.sol');
export const RuliCrowdsale = artifacts.require('RuliCrowdsale.sol');
export const cap = ruli(crowdsaleParams.cap);
export const rate = new BigNumber(crowdsaleParams.rate);
// export const ruliFundAddress = crowdsaleParams.ruliFundAddress;
export const ruliFundAddress = "0xd34da9604e5e9c2a9cc0aa481b6b24a72af3253b";
export const initialRuliFundBalance = ruli(crowdsaleParams.initialRuliFundBalance);
export const goal = ruli(crowdsaleParams.goal);
