pragma solidity ^0.4.15;

import 'zeppelin/contracts/crowdsale/CappedCrowdsale.sol';
import 'zeppelin/contracts/crowdsale/RefundableCrowdsale.sol';
import 'zeppelin/contracts/token/MintableToken.sol';
import './RuliToken.sol';

contract RuliCrowdsale is CappedCrowdsale, RefundableCrowdsale {

  function RuliCrowdsale(
    uint256 _startBlock,
    uint256 _endBlock,
    uint _rate,
    address _wallet,
    uint256 _cap,
    uint256 _initialRuliFundBalance,
    uint256 _goal
    )
    Crowdsale(_startBlock, _endBlock, _rate, _wallet)
    CappedCrowdsale(_cap)
    RefundableCrowdsale(_goal)
    {
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
  function getRate() returns (uint256) {
    uint256 currentRate = rate;

    // 2018/01/01/ 00:00 UTC
    if (now <= 1514732400) {
      currentRate = 20000;
    }
    return currentRate;
  }
}
