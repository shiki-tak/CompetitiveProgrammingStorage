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
}
