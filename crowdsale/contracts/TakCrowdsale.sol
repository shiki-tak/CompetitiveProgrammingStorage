pragma solidity ^0.4.15;

import 'zeppelin-solidity/contracts/crowdsale/CappedCrowdsale.sol';
import 'zeppelin-solidity/contracts/crowdsale/RefundableCrowdsale.sol';
import 'zeppelin-solidity/contracts/token/MintableToken.sol';
import 'zeppelin-solidity/contracts/lifecycle/Pausable.sol';
import './TakToken.sol';


contract TakCrowdsale is CappedCrowdsale, RefundableCrowdsale, Pausable {

  uint256 constant RATE_PRE_SLAE = 20000;
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
    uint256 _goal
    )
    Crowdsale(_startBlock, _endBlock, _baseRate, _wallet)
    CappedCrowdsale(_cap)
    RefundableCrowdsale(_goal) {
      
    }


}
