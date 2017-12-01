import ruli from '../utilities/ruli';
import advanceToBlock from './helpers/advanceToBlock';
import EVMThrow from './helpers/EVMThrow';

const fs = require('fs');

const crowdsaleParams = JSON.parse(fs.readFileSync('../config/Crowdsale.json', 'utf8'));
const BigNumber = web3.BigNumber;

require('chai')
  .use(require('chai-as-promised'))
  .use(require('chai-bignumber')(BigNumber))
  .should();

const RuliCrowdsale = artifacts.require('RuliCrowdsale');
const RuliToken = artifacts.require('RuliToken');

contract('RuliCrowdsale', ([wallet]) => {
  const cap = ruli(crowdsaleParams.cap);
  const rate = crowdsaleParams.rate;
  const initialRuliFundBalance = ruli(crowdsaleParams.initialRuliFundBalance);

  const lessThanCap = cap.div(3);

  beforeEach(async function () {
    this.startBlock = web3.eth.blockNumber + 10;
    this.endBlock = web3.eth.blockNumber + 20;

    this.crowdsale = await RuliCrowdsale.new(this.startBlock, this.endBlock, rate, wallet,
      cap, initialRuliFundBalance);

    this.token = RuliToken.at(await this.crowdsale.token());
  });

  describe('creating a valid crowdsale', () => {
    it('should fail with zero cap', async function () {
      await RuliCrowdsale.new(this.startBlock, this.endBlock, rate, wallet, 0, initialRuliFundBalance)
        .should.be.rejectedWith(EVMThrow);
    });

    it('should total supply of RULI token be 300 million', async function () {
      const expect = ruli(300000000);
      const tokenCap = await this.crowdsale.cap();
      await tokenCap.toNumber().should.be.bignumber.equal(expect);
    });
  });

  describe('accepting payments', () => {
    beforeEach(async function () {
      await advanceToBlock(this.startBlock - 1);
    });

    it('should accept payments within cap', async function () {
      await this.crowdsale.send(cap.minus(lessThanCap)).should.be.fulfilled;
    });

    it('should accept payments just cap', async function () {
      await this.crowdsale.send(cap.minus(lessThanCap)).should.be.fulfilled;
      await this.crowdsale.send(lessThanCap).should.be.fulfilled;
    });

    it('should reject payments outside cap', async function () {
      await this.crowdsale.send(cap);
      await this.crowdsale.send(1).should.be.rejectedWith(EVMThrow);
    });

    it('should reject payments that exceed cap', async function () {
      await this.crowdsale.send(cap.plus(1)).should.be.rejectedWith(EVMThrow);
    });
  });

  describe('ending', () => {
    beforeEach(async function () {
      await advanceToBlock(this.startBlock - 1);
    });

    it('should not be ended if under cap', async function () {
      let hasEnded = await this.crowdsale.hasEnded();
      hasEnded.should.equal(false);
      await this.crowdsale.send(lessThanCap);
      hasEnded = await this.crowdsale.hasEnded();
      hasEnded.should.equal(false);
    });

    it('should not be ended if just under cap', async function () {
      await this.crowdsale.send(cap.minus(1));
      const hasEnded = await this.crowdsale.hasEnded();
      hasEnded.should.equal(false);
    });

    it('should be ended if cap reached', async function () {
      await this.crowdsale.send(cap);
      const hasEnded = await this.crowdsale.hasEnded();
      hasEnded.should.equal(true);
    });
  });
});
