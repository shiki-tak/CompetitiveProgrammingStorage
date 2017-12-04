import advanceToBlock from './helpers/advanceToBlock';
import EVMThrow from './helpers/EVMThrow';

import { RuliToken, RuliCrowdsale, cap, rate,
  initialRuliFundBalance, goal, should } from './helpers/ruli_helper';

contract('RuliCrowdsale', ([owner, wallet, thirdparty]) => {
  beforeEach(async function () {
    this.startBlock = web3.eth.blockNumber + 10;
    this.endBlock = web3.eth.blockNumber + 20;

    this.crowdsale = await RuliCrowdsale.new(this.startBlock, this.endBlock, rate, wallet,
      cap, initialRuliFundBalance, goal, { from: owner });

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
