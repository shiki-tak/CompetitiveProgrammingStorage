import ruli from '../utilities/ruli';
import ether from './helpers/ether';
import advanceToBlock from './helpers/advanceToBlock';
import EVMThrow from './helpers/EVMThrow';

import { RuliToken, RuliCrowdsale, ruliFundAddress, cap, rate, initialRuliFundBalance, should } from './helpers/ruli_helper';

contract('RuliCrowdSale', ([investor, wallet, purchaser]) => {
  const someOfTokenAmount = ether(42);
  const expectedTokenAmount = rate.mul(someOfTokenAmount);

  const expectedInitialTokenAmount = expectedTokenAmount.add(initialRuliFundBalance);

  beforeEach(async function () {
    this.startBlock = web3.eth.blockNumber + 10;
    this.endBlock = web3.eth.blockNumber + 20;

    this.crowdsale = await RuliCrowdsale.new(this.startBlock, this.endBlock, rate, wallet, cap, initialRuliFundBalance);

    this.token = RuliToken.at(await this.crowdsale.token());
  });

  describe('initialized correctly', () => {

    it('should be correct fund address', async function () {
      const expect = web3.eth.accounts[3];
      const cs = await RuliCrowdsale.new(this.startBlock, this.endBlock, rate, ruliFundAddress, cap, initialRuliFundBalance);
      const actual = await cs.wallet();
      actual.should.be.equal(expect);
    });

    it('should token be instance of RuliToken', async function () {
      this.token.should.be.an.instanceof(RuliToken);
    });

    it('should RULI fund has 150 million tokens.', async function () {
      const expect = ruli(150000000);
      const actual = await this.token.balanceOf(wallet);
      await actual.should.be.bignumber.equal(expect);
    });

    it('should total supply be 150 million tokens.', async function () {
      const expect = ruli(150000000);
      const actual = await this.token.totalSupply();
      await actual.should.be.bignumber.equal(expect);
    });

    it('should offering amount be 150 million tokens.', async function () {
      const expect = ruli(150000000);
      const totalSupply = await this.token.totalSupply();
      const crowdSaleCap = await this.crowdsale.cap();
      const actual = crowdSaleCap.sub(totalSupply);
      await actual.should.be.bignumber.equal(expect);
    });
  });

  describe('token owner', () => {
    it('should be token owner', async function () {
      const owner = await this.token.owner();
      owner.should.equal(this.crowdsale.address);
    });
  });

  describe('accepting payments', () => {
    it('should reject payments before start', async function () {
      await this.crowdsale.send(someOfTokenAmount).should.be.rejectedWith(EVMThrow);
      await this.crowdsale.buyTokens(investor, {from: purchaser, value: someOfTokenAmount}).should.be.rejectedWith(EVMThrow);
    });

    it('should accept payments after start', async function () {
      await advanceToBlock(this.startBlock - 1);
      await this.crowdsale.send(someOfTokenAmount).should.be.fulfilled;
      await this.crowdsale.buyTokens(investor, {value: someOfTokenAmount, from: purchaser}).should.be.fulfilled;
    });

    it('should reject payments after end', async function () {
      await advanceToBlock(this.endBlock);
      await this.crowdsale.send(someOfTokenAmount).should.be.rejectedWith(EVMThrow);
      await this.crowdsale.buyTokens(investor, {value: someOfTokenAmount, from: purchaser}).should.be.rejectedWith(EVMThrow);
    });
  });

  describe('token amount adjustments', () => {
    it('should fund has 150 million tokens after received ether', async function () {
      await advanceToBlock(this.startBlock - 1);
      await this.crowdsale.send(someOfTokenAmount);
      const expect = ruli(150000000);
      const actual = await this.token.balanceOf(wallet);
      await actual.should.be.bignumber.equal(expect);
    });

    // initial + (received ether * decimals ) = total supply
    // 150,000,000 + ( 10,000 * 2,800 ) = 178,000,000
    it('should total supply be 1.78 million tokens ever if received 10,000 ether', async function () {
      await advanceToBlock(this.startBlock - 1);
      await this.crowdsale.send(ether(10000));
      const expect = ruli(178000000);
      const actual = await this.token.totalSupply();
      await actual.should.be.bignumber.equal(expect);
    });
  });

  describe('high-level purchase', () => {
    beforeEach(async function () {
      await advanceToBlock(this.startBlock);
    });

    it('should log purchase', async function () {
      const { logs } = await this.crowdsale.sendTransaction({ value: someOfTokenAmount, from: investor });

      const event = logs.find(e => e.event === 'TokenPurchase');

      should.exist(event);
      event.args.purchaser.should.equal(investor);
      event.args.beneficiary.should.equal(investor);
      event.args.value.should.be.bignumber.equal(someOfTokenAmount);
      event.args.amount.should.be.bignumber.equal(expectedTokenAmount);
    });

    it('should increase totalSupply', async function () {
      await this.crowdsale.send(someOfTokenAmount);
      const totalSupply = await this.token.totalSupply();
      totalSupply.should.be.bignumber.equal(expectedInitialTokenAmount);
    });

    it('should assign tokens to sender', async function () {
      await this.crowdsale.sendTransaction({ value: someOfTokenAmount, from: investor });
      const balance = await this.token.balanceOf(investor);
      balance.should.be.bignumber.equal(expectedTokenAmount);
    });

    it('should forward funds to wallet', async function () {
      const pre = web3.eth.getBalance(wallet);
      await this.crowdsale.sendTransaction({ value: someOfTokenAmount, from: investor });
      const post = web3.eth.getBalance(wallet);
      post.minus(pre).should.be.bignumber.equal(someOfTokenAmount);
    });
  });

  describe('low-level purchase', () => {
    beforeEach(async function () {
      await advanceToBlock(this.startBlock);
    });

    it('should log purchase', async function () {
      const { logs } = await this.crowdsale.buyTokens(investor, { value: someOfTokenAmount, from: purchaser });

      const event = logs.find(e => e.event === 'TokenPurchase');

      should.exist(event);
      event.args.purchaser.should.equal(purchaser);
      event.args.beneficiary.should.equal(investor);
      event.args.value.should.be.bignumber.equal(someOfTokenAmount);
      event.args.amount.should.be.bignumber.equal(expectedTokenAmount);
    });

    it('should increase totalSupply', async function () {
      await this.crowdsale.buyTokens(investor, { value: someOfTokenAmount, from: purchaser });
      const totalSupply = await this.token.totalSupply();
      totalSupply.should.be.bignumber.equal(expectedInitialTokenAmount);
    });

    it('should assign tokens to beneficiary', async function () {
      await this.crowdsale.buyTokens(investor, { value: someOfTokenAmount, from: purchaser });
      const balance = await this.token.balanceOf(investor);
      balance.should.be.bignumber.equal(expectedTokenAmount);
    });

    it('should forward funds to wallet', async function () {
      const pre = web3.eth.getBalance(wallet);
      await this.crowdsale.buyTokens(investor, { value: someOfTokenAmount, from: purchaser });
      const post = web3.eth.getBalance(wallet);
      post.minus(pre).should.be.bignumber.equal(someOfTokenAmount);
    });
  });

  describe('ending', () => {
    it('should be ended only after end', async function () {
      let ended = await this.crowdsale.hasEnded();
      ended.should.equal(false);
      await advanceToBlock(this.endBlock + 1);
      ended = await this.crowdsale.hasEnded();
      ended.should.equal(true);
    });
  });
});
