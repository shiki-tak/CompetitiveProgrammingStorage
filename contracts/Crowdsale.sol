pragma solidity ^0.4.15;

import './RuliToken.sol';

contract Crowdsale {

  RuliToken public token;
  address public fund;
  uint public rate;

  function Crowdsale(address _RuliTokenAddress, address _fundAddress, uint _rate) {
    token = RuliToken(_RuliTokenAddress);
    fund = _fundAddress;
    rate = _rate;
  }
}
