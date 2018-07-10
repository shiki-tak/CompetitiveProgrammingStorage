pragma solidity ^0.4.15;

import 'zeppelin/contracts/crowdsale/CappedCrowdsale.sol';
import 'zeppelin/contracts/crowdsale/RefundableCrowdsale.sol';
import 'zeppelin/contracts/token/MintableToken.sol';
import './RuliToken.sol';

contract RuliCrowdsale is CappedCrowdsale, RefundableCrowdsale {
  // Seconds of one week. (60 * 60 * 24 * 7) = 604,800
  uint256 constant WEEK = 604800;

  /*
  * Token exchange rates of ETH and RULI.
  */
  uint256 public ratePreSale;
  uint256 public rateWeek1;
  uint256 public rateWeek2;
  uint256 public rateWeek3;

  function RuliCrowdsale(
    uint256 _startBlock,
    uint256 _endBlock,
    uint256 _baseRate,
    address _wallet,
    uint256 _cap,
    uint256 _initialRuliFundBalance,
    uint256 _goal,
    uint256 _ratePreSale,
    uint256 _rateWeek1,
    uint256 _rateWeek2,
    uint256 _rateWeek3
    )
    Crowdsale(_startBlock, _endBlock, _baseRate, _wallet)
    CappedCrowdsale(_cap)
    RefundableCrowdsale(_goal)
    {
      ratePreSale = _ratePreSale;
      rateWeek1 = _rateWeek1;
      rateWeek2 = _rateWeek2;
      rateWeek3 = _rateWeek3;

      token.mint(wallet, _initialRuliFundBalance);
  }

  function createTokenContract() internal returns (MintableToken) {
    return new RuliToken();
  }

  // overriding RefundableCrowdsale#finalization to store remaining tokens.
  function finalization() internal {
    uint256 remaining = cap.sub(token.totalSupply());

    if (remaining > 0) {
      token.mint(wallet, remaining);
    }
    super.finalization();
  }

  // overriding Crowdsale#buyTokens to rate customizable.
  // This is created to compatible PR below:
  // - https://github.com/OpenZeppelin/zeppelin-solidity/pull/317
  function buyTokens(address beneficiary) payable {
    require(beneficiary != 0x0);
    require(validPurchase());

    uint256 weiAmount = msg.value;

    // calculate token amount to be created
    uint256 tokens = weiAmount.mul(getRate());

    // update state
    weiRaised = weiRaised.add(weiAmount);

    token.mint(beneficiary, tokens);
    TokenPurchase(msg.sender, beneficiary, weiAmount, tokens);

    forwardFunds();
  }

  // Custom rate.
  //
  // This is created to compatible PR below:
  // - https://github.com/OpenZeppelin/zeppelin-solidity/pull/317
  function getRate() constant returns (uint256) {
    uint256 currentRate = rate;

    uint256 tokenSaleStartTimeStamp = 1514732400;

    // 2018/01/01/ 00:00 UTC
    if (now <= tokenSaleStartTimeStamp) {
      // before 2018/01/01/ 00:00 UTC
      currentRate = ratePreSale;
    } else if (now <= tokenSaleStartTimeStamp.add(WEEK)) {
      // before 2018/01/08/ 00:00 UTC
      currentRate = rateWeek1;
    } else if (now <= tokenSaleStartTimeStamp.add(WEEK.mul(2))) {
      // before 2018/01/15/ 00:00 UTC
      currentRate = rateWeek2;
    } else if (now <= tokenSaleStartTimeStamp.add(WEEK.mul(3))) {
      // before 2018/01/22/ 00:00 UTC
      currentRate = rateWeek3;
    }
    return currentRate;
  }
}
