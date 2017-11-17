pragma solidity ^0.4.15;

import 'zeppelin-solidity/contracts/crowdsale/CappedCrowdsale.sol';
import 'zeppelin-solidity/contracts/crowdsale/RefundableCrowdsale.sol';
import 'zeppelin-solidity/contracts/token/MintableToken.sol';
import 'zeppelin-solidity/contracts/lifecycle/Pausable.sol';
import './WhitelistedCrowdsale.sol';
import './TakToken.sol';


contract TakCrowdsale is CappedCrowdsale, RefundableCrowdsale, Pausable, WhitelistedCrowdsale {

  uint256 constant RATE_PRE_SALE = 20000;
  uint256 constant RATE_WEEK_1 = 2900;
  uint256 constant RATE_WEEK_2 = 2600;
  uint256 constant RATE_WEEK_3 = 2300;

  // ICO start date time.
  uint256 public icoStartTime;

  // The cap amount of TAK tokens.
  uint256 public tokenCap;

  function TakCrowdsale (
    uint256 _startBlock,
    uint256 _icoStartTime,
    uint256 _endBlock,
    uint256 _baseRate,
    address _wallet,
    uint256 _cap,
    uint256 _tokenCap,
    uint256 _initialTakFundBalance,
    uint256 _goal,
    address[] _whiteList
    )
    // 継承元のcontractのconstractorを実行する
    Crowdsale(_startBlock, _endBlock, _baseRate, _wallet)
    CappedCrowdsale(_cap)
    RefundableCrowdsale(_goal)
    WhitelistedCrowdsale(_whiteList)
    {
      icoStartTime = _icoStartTime;
      tokenCap = _tokenCap;

      // Tokenの総量
      token.mint(wallet, _initialTakFundBalance);
    }

    // overriding Crowdsale#createTokenContract to change token to TakToken.
    function createTokenContract() internal returns (MintableToken) {
      return new TakToken();
    }

    // overriding CappedCrowdsale#validPurchase to add extra token cap logic
    // @return true if investors can buy at the moment
    function validPurchase() internal constant returns (bool) {
      bool withinTokenCap = token.totalSupply().add(msg.value.mul(getRate())) <= tokenCap;
      return super.validPurchase() && withinTokenCap;
    }

    // overriding CappedCrowdsale#hasEnded to add token cap logic
    // @return true if crowsale event has ended
    function hasEnded() public constant returns (bool) {
      uint256 threshold = tokenCap.div(100).mul(99);
      bool thresholdReached = token.totalSupply() >= threshold;
      return super.hasEnded() || thresholdReached;
    }

    // overriding RefundableCrowdsale#finalization
    // - To store remaining Tak tokens.
    // - To minting unfinished because of our consensus algorithm.
    function finalization() internal {
      uint256 remaining = tokenCap.sub(token.totalSupply());
      if (remaining > 0) {
        token.mint(wallet, remaining);
      }

      // change TakToken owner to TakFund.
      token.transferOwnership(wallet);

      // From RefundableCrowdsale#finalization
      if (goalReached()) {
        vault.close();
      } else {
        vault.enableRefunds();
      }
    }

    // overriding Crowdsale#buyTokens to rate customizable.
    // This is created to compatible PR below.
    function buyTokens(address beneficiary) payable {
      require(!paused);
      require(beneficiary != 0x0);
      require(validPurchase());
      require(saleAccepting());

      uint256 weiAmount = msg.value;

      // for presale
      if (isPresale()) {
        checkLimit(weiAmount);
      }

      // calculate token amount to be created
      uint256 tokens = weiAmount.mul(getRate());

      // update state.
      weiRaised = weiRaised.add(weiAmount);

      token.mint(beneficiary, tokens);
      TokenPurchase(msg.sender, beneficiary, weiAmount, tokens);

      forwardFunds();
    }

    // Custome rate.
    //
    // This is created to compatible PR below.
    function getRate() constant returns (uint256) {
      uint256 currentRate = rate;

      if (isPresale()) {
        // before 2017/11/01 02:00 UTC
        currentRate = RATE_PRE_SALE;
      } else if (now <= icoStartTime.add(1 weeks)) {
        // before 2017/11/08 02:00 UTC
        currentRate = RATE_WEEK_1;
      } else if (now <= icoStartTime.add(2 weeks)) {
        // before 2017/11/15 02:00 UTC
        currentRate = RATE_WEEK_2;
      } else if (now <= icoStartTime.add(3 weeks)) {
        // before 2017/11/22 02:00 UTC
        currentRate = RATE_WEEK_3;
      }
      return currentRate;
    }

    // @return true if crowd sale is accepting.
    function saleAccepting() internal constant returns (bool) {
      return !isPresale();
    }

    // @return true if crowd sale is accepting.
    function isPresale() internal constant returns (bool) {
      return now <= icoStartTime;
    }
}
