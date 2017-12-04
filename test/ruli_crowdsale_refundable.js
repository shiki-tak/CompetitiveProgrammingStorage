import ether from './helpers/ether';
import advanceToBlock from './helpers/advanceToBlock';
import EVMThrow from './helpers/EVMThrow';

import { RuliCrowdsale, cap, rate, initialRuliFundBalance, goal, BigNumber } from './helpers/ruli_helper';

contract('RuliCrowdsale', ([owner, wallet, investor, notInvestor]) => {
  const lessThanGoal = ether(goal).minus(ether(100));

  beforeEach(async function () {
    this.startBlock = web3.eth.blockNumber + 10;
    this.endBlock = web3.eth.blockNumber + 20;

    this.crowdsale = await RuliCrowdsale.new(this.startBlock, this.endBlock, rate, wallet,
    cap, initialRuliFundBalance, ether(goal), { from: owner });
  });

  describe('creating a valid refundable crowdsale', () => {
    it('should fail with zero goal', async function () {
      await RuliCrowdsale.new(this.startBlock, this.endBlock, rate, wallet, cap, initialRuliFundBalance, ether(goal));
    });

    it('should goal be 700 ether', async function () {
      const expect = ether(700);
      const actual = await this.crowdsale.goal();
      await actual.should.be.bignumber.equal(expect);
    });

    it('should goal be 105, 000,000 JPY', async function () {
      const goalAsJPY = new BigNumber(10500000);
      const expectedEtherPrice = new BigNumber(15000);
      const convertedGoal = expectedEtherPrice.times(goal);
      await goalAsJPY.should.be.bignumber.equal(convertedGoal);
    });
  });

  it('should has enough RULI token to reach the goal', async function () {
    let hasEnded = await this.crowdsale.hasEnded();
    hasEnded.should.equal(false);
    await advanceToBlock(this.startBlock - 1);
    await this.crowdsale.sendTransaction({ value: ether(goal), from: investor });
    hasEnded = await this.crowdsale.hasEnded();
    hasEnded.should.equal(false);
  });

  describe('deny refunds', () => {
    it('should deny refunds before end', async function () {
      await this.crowdsale.claimRefund({ from: investor }).should.be.rejectedWith(EVMThrow);
      await advanceToBlock(this.endBlock - 1);
      await this.crowdsale.claimRefund({ from: investor }).should.be.rejectedWith(EVMThrow);
    });

    it('should deny refunds after end if goal was reached', async function () {
      await advanceToBlock(this.startBlock - 1);
      await this.crowdsale.sendTransaction({ value: ether(goal), from: investor });
      await advanceToBlock(this.endBlock);
      await this.crowdsale.claimRefund({ from: investor }).should.be.rejectedWith(EVMThrow);
    });
  });

  describe('allow refunds', () => {
    it('should allow refunds after end if goal was not reached', async function () {
      await advanceToBlock(this.startBlock - 1);
      await this.crowdsale.sendTransaction({ value: lessThanGoal, from: investor });
      await advanceToBlock(this.endBlock);
      await this.crowdsale.finalize({ from: owner });

      const pre = web3.eth.getBalance(investor);
      // TODO: gasPrice
      await this.crowdsale.claimRefund( { from: investor, gasPrice: 0 })
        .should.be.fulfilled;
      const post = web3.eth.getBalance(investor);
      post.minus(pre).should.be.bignumber.equal(lessThanGoal);
    });
  });

  it('should return 0 ether to non investors', async function () {
    await advanceToBlock(this.startBlock - 1);
    await this.crowdsale.sendTransaction({ value: lessThanGoal, from: investor });
    await advanceToBlock(this.endBlock);
    await this.crowdsale.finalize({ from: owner });
    const pre = web3.eth.getBalance(notInvestor);
    await this.crowdsale.claimRefund({ from: notInvestor, gasPrice: 0 }).should.be.fulfilled;
    const post = web3.eth.getBalance(notInvestor);
    post.should.be.bignumber.equal(pre);
  });

  describe('goal was reached', () => {
    it('should forward funds to wallet after end', async function () {
      await advanceToBlock(this.startBlock - 1);
      await this.crowdsale.sendTransaction({ value: ether(goal), from: investor });
      await advanceToBlock(this.endBlock);

      const pre = web3.eth.getBalance(wallet);
      await this.crowdsale.finalize({ from: owner });
      const post = web3.eth.getBalance(wallet);

      post.minus(pre).should.be.bignumber.equal(ether(goal));
    });
  });
});
