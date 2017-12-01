import ruli from '../utilities/ruli';
import advanceToBlock from './helpers/advanceToBlock';
import EVMThrow from './helpers/EVMThrow';

const RuliToken = artifacts.require('RuliToken.sol');
const Crowdsale = artifacts.require('RuliCrowdsale.sol');

const fs = require('fs');
const crowdsaleParams = JSON.parse(fs.readFileSync('../config/Crowdsale.json', 'utf8'));

const BigNumber = web3.BigNumber;

const should = require('chai')
  .use(require('chai-as-promised'))
  .use(require('chai-bignumber')(BigNumber))
  .should();

contract('RuliCrowdSale', ([investor, wallet, purchaser]) => {
  const cap = ruli(crowdsaleParams.cap);
  const rate = new BigNumber(crowdsaleParams.rate);
  const initialRuliFundBalance = ruli(crowdsaleParams.initialRuliFundBalance);

  const value = ruli(42);

  const expectedTokenAmount = rate.mul(value);
  const expectedInitialTokenAmount = expectedTokenAmount.add(initialRuliFundBalance);

  beforeEach(async function () {
    this.startBlock = web3.eth.blockNumber + 10;
    this.endBlock = web3.eth.blockNumber + 20;

    this.crowdsale = await Crowdsale.new(this.startBlock, this.endBlock, rate, wallet, cap, initialRuliFundBalance);

    this.token = RuliToken.at(await this.crowdsale.token());
  });

  describe('initialized correctly', () => {
    it('should be correct token name', async function () {
      const expect = 'RuliToken';
      const actual = await this.token.name();
      actual.should.be.equal(expect);
    });

    it('should be correct token symbol', async function () {
      const expect = 'RULI';
      const actual = await this.token.symbol();
      actual.should.be.equal(expect);
    });

    it('should be correct token decimals', async function () {
      const expect = 18;
      const actual = await this.token.decimals();
      actual.toNumber().should.be.equal(expect);
    });

    it('should be same decimals of ether', async function () {
      const expect = web3.toWei(1, 'ether');
      const tokenDecimals = await this.token.decimals();
      const actual = new web3.BigNumber(1 * Math.pow(10, tokenDecimals))
    })

    it('should be correct fund address', async function () {
      const expect = "0xd4232bdbc4cdbdc9d131103de55b9a2b68d45c78";
      const ruliFundAddress = crowdsaleParams.ruliFundAddress;
      const cs = await Crowdsale.new(this.startBlock, this.endBlock, rate, ruliFundAddress, cap, initialRuliFundBalance);
      const actual = await cs.wallet();
      actual.should.be.equal(expect);
    });

    it('should token be instance of RuliToken', async function () {
      this.token.should.be.an.instanceof(RuliToken);
    });

    it('should fund has 150 million tokens.', async function () {
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

  it('should be token owner', async function () {
    const owner = await this.token.owner();
    owner.should.equal(this.crowdsale.address);
  });

  it('should be ended only after end', async function () {
    let ended = await this.crowdsale.hasEnded();
    ended.should.equal(false);
    await advanceToBlock(this.endBlock + 1);
    ended = await this.crowdsale.hasEnded();
    ended.should.equal(true);
  });

  describe('accepting payments', () => {
    it('should reject payments before start', async function () {
      await this.crowdsale.send(value).should.be.rejectedWith(EVMThrow);
      await this.crowdsale.buyTokens(investor, value, {from: purchaser}).should.be.rejectedWith(EVMThrow);
    });

    it('should accept payments after start', async function () {
      await advanceToBlock(this.startBlock - 1);
      await this.crowdsale.send(value).should.be.fulfilled;
      await this.crowdsale.buyTokens(investor, {value: value, from: purchaser}).should.be.fulfilled;
    });

    it('should reject payments after end', async function () {
      await advanceToBlock(this.endBlock);
      await this.crowdsale.send(value).should.be.rejectedWith(EVMThrow);
      await this.crowdsale.buyTokens(investor, {value: value, from: purchaser}).should.be.rejectedWith(EVMThrow);
    });
  });

  describe('high-level purchase', () => {
    beforeEach(async function () {
      await advanceToBlock(this.startBlock);
    });

    it('should log purchase', async function () {
      const { logs } = await this.crowdsale.sendTransaction({ value: value, from: investor });

      const event = logs.find(e => e.event === 'TokenPurchase');

      should.exist(event);
      event.args.purchaser.should.equal(investor);
      event.args.beneficiary.should.equal(investor);
      event.args.value.should.be.bignumber.equal(value);
      event.args.amount.should.be.bignumber.equal(expectedTokenAmount);
    });

    it('should increase totalSupply', async function () {
      await this.crowdsale.send(value);
      const totalSupply = await this.token.totalSupply();
      totalSupply.should.be.bignumber.equal(expectedInitialTokenAmount);
    });

    it('should assign tokens to sender', async function () {
      await this.crowdsale.sendTransaction({ value: value, from: investor });
      const balance = await this.token.balanceOf(investor);
      balance.should.be.bignumber.equal(expectedTokenAmount);
    });

    it('should forward funds to wallet', async function () {
      const pre = web3.eth.getBalance(wallet);
      await this.crowdsale.sendTransaction({ value, from: investor });
      const post = web3.eth.getBalance(wallet);
      post.minus(pre).should.be.bignumber.equal(value);
    });
  });

  describe('low-level purchase', () => {
    beforeEach(async function () {
      await advanceToBlock(this.startBlock);
    });

    it('should log purchase', async function () {
      const { logs } = await this.crowdsale.buyTokens(investor, { value: value, from: purchaser });

      const event = logs.find(e => e.event === 'TokenPurchase');

      should.exist(event);
      event.args.purchaser.should.equal(purchaser);
      event.args.beneficiary.should.equal(investor);
      event.args.value.should.be.bignumber.equal(value);
      event.args.amount.should.be.bignumber.equal(expectedTokenAmount);
    });

    it('should increase totalSupply', async function () {
      await this.crowdsale.buyTokens(investor, { value, from: purchaser });
      const totalSupply = await this.token.totalSupply();
      totalSupply.should.be.bignumber.equal(expectedInitialTokenAmount);
    });

    it('should assign tokens to beneficiary', async function () {
      await this.crowdsale.buyTokens(investor, { value, from: purchaser });
      const balance = await this.token.balanceOf(investor);
      balance.should.be.bignumber.equal(expectedTokenAmount);
    });

    it('should forward funds to wallet', async function () {
      const pre = web3.eth.getBalance(wallet);
      await this.crowdsale.buyTokens(investor, { value, from: purchaser });
      const post = web3.eth.getBalance(wallet);
      post.minus(pre).should.be.bignumber.equal(value);
    });
  });
});
