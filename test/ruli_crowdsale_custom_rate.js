import moment from 'moment';
import ether from './helpers/ether';
import advanceToBlock from './helpers/advanceToBlock';
import increaseTime from './helpers/increaseTime';

import { RuliCrowdsale, cap, rate, initialRuliFundBalance, goal } from './helpers/ruli_helper';

contract('RuliCrowdsale', ([owner, wallet]) => {
  beforeEach(async function () {
    this.startBlock = web3.eth.blockNumber + 10;
    this.endBlock = web3.eth.blockNumber + 20;

    this.crowdsale = await RuliCrowdsale.new(this.startBlock, this.endBlock, rate, wallet,
      cap, initialRuliFundBalance, ether(goal), { from: owner });
  });

  describe('creating a valid refundable crowdsale', () => {
    it('should initial rate be 20,000 RULI', async function () {
      const expect = 20000; // pre sale
      await advanceToBlock(this.endBlock - 1);
      const actual = await this.crowdsale.getRate();
      await actual.should.be.bignumber.equal(expect);
    });

    it('should base rate be 2,000 RULI', async function () {
      // Increase current time to ICO start datetime.
      // TODO: refactoring
      const now = await Math.floor(Date.now() / 1000);
      const increaseDuration = 1504231200 - now;
      await increaseTime(moment.duration(increaseDuration + 100, 'second'));

      const expect = 2000; // base
      await advanceToBlock(this.endBlock - 1);
      const actual = await this.crowdsale.getRate();
      await actual.should.be.bignumber.equal(expect);
    });
  });
});
