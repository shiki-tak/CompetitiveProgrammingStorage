import moment from 'moment';
import ruli from '../../utilities/ruli';
import increaseTime from '../helpers/increaseTime';

const fs = require('fs');
const chai = require('chai');
const chaiAsPromised = require('chai-as-promised');
const chaiBigNumber = require('chai-bignumber');

const crowdsaleParams
= JSON.parse(fs.readFileSync('/Users/user1/Desktop/workspace/ico-contracts/config/Crowdsale.json', 'utf8'));

export const BigNumber = web3.BigNumber;
export const should = chai
  .use(chaiAsPromised)
  .use(chaiBigNumber(BigNumber))
  .should();

export const RuliToken = artifacts.require('RuliToken.sol');
export const RuliCrowdsale = artifacts.require('RuliCrowdsale.sol');
export const cap = ruli(crowdsaleParams.cap);
export const rate = new BigNumber(crowdsaleParams.rate);
// export const ruliFundAddress = crowdsaleParams.ruliFundAddress;
export const ruliFundAddress = '0xd34da9604e5e9c2a9cc0aa481b6b24a72af3253b';
export const initialRuliFundBalance = ruli(crowdsaleParams.initialRuliFundBalance);
export const goal = new BigNumber(crowdsaleParams.goal);

export async function setTimeingToTokenSaleStart() {
  const now = await Math.floor(Date.now() / 1000);
  const IncreaseDuration = 1514732400 - now;
  await increaseTime(moment.duration(IncreaseDuration, 'second'));
}

// Set time to after week4 when token rate is base.
export async function setTimeingToBaseTokenRate() {
  await setTimeingToTokenSaleStart();
  await increaseTime(moment.duration(3, 'weeks'));
}
