import ruli from '../utilities/ruli';
import ether from './helpers/ether';
import advanceToBlock from './helpers/advanceToBlock';
import EVMThrow from './helpers/EVMThrow';

import { RuliToken, RuliCrowdsale, cap, rate, initialRuliFundBalance, goal } from './helpers/ruli_helper';

contract('RuliCrowdsale', ([wallet]) => {
  const lessThanCap = cap.div(10);

  beforeEach(async function () {
    this.startBlock = web3.eth.blockNumber + 10;
    this.endBlock = web3.eth.blockNumber + 20;

    this.crowdsale = await RuliCrowdsale.new(
      this.startBlock, this.endBlock, rate.base, wallet,
      cap, initialRuliFundBalance, goal,
      rate.preSale, rate.week1, rate.week2, rate.week3
    );

    this.token = RuliToken.at(await this.crowdsale.token());
  });

  describe('creating a valid capped crowdsale', () => {
    it('should fail with zero cap', async function () {
      await RuliCrowdsale.new(this.startBlock, this.endBlock,
        rate.base, wallet, 0, initialRuliFundBalance, ether(goal),
        rate.preSale, rate.week1, rate.week2, rate.week3)
        .should.be.rejectedWith(EVMThrow);
    });

    it('should cap of RULI token be 300 million', async function () {
      const expect = ruli(300000000);
      const tokenCap = await this.crowdsale.cap();
      await tokenCap.toNumber().should.be.bignumber.equal(expect);
    });
  });

  describe('accepting payments with cap', () => {
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

  describe('ending with cap', () => {
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

    it('should not be ended even if immediately before cap', async function () {
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
