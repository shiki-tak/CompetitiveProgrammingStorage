pragma solidity ^0.4.15;

import './RuliToken.sol';

contract Crowdsale {

  RuliToken public token;
  address public fund;

  function Crowdsale(address _RuliTokenAddress, address _fundAddress) {
    token = RuliToken(_RuliTokenAddress);
    fund = _fundAddress;
  }
}
