import ruli from '../utilities/ruli';
import ether from './helpers/ether';
import advanceToBlock from './helpers/advanceToBlock';
import EVMThrow from './helpers/EVMThrow';

import { RuliToken, RuliCrowdsale, cap, rate,
  initialRuliFundBalance, goal, should, setTimeingToBaseTokenRate } from './helpers/ruli_helper';

contract('RuliCrowdsale', ([owner, wallet, thirdparty]) => {

  before(async () => {
    await setTimeingToBaseTokenRate();
  });

  beforeEach(async function () {
    this.startBlock = web3.eth.blockNumber + 10;
    this.endBlock = web3.eth.blockNumber + 20;

    this.crowdsale = await RuliCrowdsale.new(this.startBlock, this.endBlock, rate.base, wallet,
      cap, initialRuliFundBalance, goal,
      rate.preSale, rate.week1, rate.week2, rate.week3, { from: owner });

    this.token = RuliToken.at(await this.crowdsale.token());
  });

  describe('finalize', () => {
    it('can be finalize by owner after ending', async function () {
      await advanceToBlock(this.endBlock);
      await this.crowdsale.finalize({ from: owner }).should.be.fulfilled;
    });

    it('logs finalize', async function () {
      await advanceToBlock(this.endBlock);
      const { logs } = await this.crowdsale.finalize({ from: owner });
      const event = logs.find(e => e.event === 'Finalized');
      should.exist(event);
    });

    it('finishes minting of token', async function () {
      await advanceToBlock(this.endBlock);
      await this.crowdsale.finalize({ from: owner });
      const finished = await this.token.mintingFinished();
      finished.should.equal(true);
    });
  });

  describe('remaining tokens', () => {
    /*
    // TODO: error fix
    it('should store to RULI fund if tokens are remain', async function () {
      await advanceToBlock(this.startBlock - 1);

      // ether * rate = sold amount
      // 50,000 * 2,800 = 140,000,000
      await this.crowdsale.send(ether(50000));

      // offered amount - sold amount = remain
      // 150,000,000 - 140,000,000 = 10,000,000
      const remainingTokens = ruli(10000000);

      let expect = ruli(150000000);
      let actual = await this.token.balanceOf(wallet);
      await actual.should.be.bignumber.equal(expect);

      await advanceToBlock(this.endBlock);
      await this.crowdsale.finalize({ from: owner });

      expect = expect.plus(remainingTokens);
      actual = await this.token.balanceOf(wallet);
      await actual.should.be.bignumber.equal(expect);
    });

    it('should not care about goal, to keep code simple', async function () {
      let expect = ruli(150000000);
      let actual = await this.token.balanceOf(wallet);
      await actual.should.be.bignumber.equal(expect);

      const goalReached = await this.crowdsale.goalReached();
      await goalReached.should.equal(false);

      await advanceToBlock(this.endBlock);
      await this.crowdsale.finalize({ from: owner });

      expect = ruli(300000000);
      actual = await this.token.balanceOf(wallet);
      await actual.should.be.bignumber.equal(expect);
    });
    */
    it('should not do anything if no remaining token', async function () {
      const capSameAsInitialRuliFundBalance = initialRuliFundBalance;
      this.crowdsale = await RuliCrowdsale.new(this.startBlock, this.endBlock, rate, wallet,
        capSameAsInitialRuliFundBalance, initialRuliFundBalance,
        goal, rate.preSale, rate.week1, rate.week2, rate.week3, { from: owner });
        this.token = RuliToken.at(await this.crowdsale.token());

        const expect = ruli(150000000);
        let actual = await this.token.balanceOf(wallet);
        await actual.should.be.bignumber.equal(expect);

        await advanceToBlock(this.endBlock);
        await this.crowdsale.finalize({ from: owner });

        actual = await this.token.balanceOf(wallet);
        await actual.should.be.bignumber.equal(expect);
    });
  });

  describe('reject finalize', () => {
    it('cannot be finalized before ending', async function () {
      await this.crowdsale.finalize({ from: owner }).should.be.rejectedWith(EVMThrow);
    });

    it('cannnot be finalized by thrid party after ending', async function () {
      await advanceToBlock(this.endBlock);
      await this.crowdsale.finalize({ from: thirdparty }).should.be.rejectedWith(EVMThrow);
    });

    it('cannot be finalized twice', async function () {
      await advanceToBlock(this.endBlock + 1);
      await this.crowdsale.finalize({ from: owner });
      await this.crowdsale.finalize({ from: owner }).should.be.rejectedWith(EVMThrow);
    });
  });
});
