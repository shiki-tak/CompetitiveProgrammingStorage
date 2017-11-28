pragma solidity ^0.4.15;

import './RuliToken.sol';

contract Crowdsale {

  RuliToken public token;
  address public fund;
  uint256 public offeredAmount;
  uint public rate;

  function Crowdsale(address _RuliTokenAddress, address _fundAddress, uint256 _offeredAmount, uint _rate) {
    token = RuliToken(_RuliTokenAddress);
    fund = _fundAddress;
    offeredAmount = _offeredAmount;
    rate = _rate;
  }
}
