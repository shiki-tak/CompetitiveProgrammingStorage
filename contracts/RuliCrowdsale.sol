pragma solidity ^0.4.15;

import 'zeppelin/contracts/crowdsale/Crowdsale.sol';
import './RuliToken.sol';

contract RuliCrowdsale is Crowdsale {

  RuliToken public token;
  address public fund;
  uint256 public offeredAmount;
  uint public rate;

  function RuliCrowdsale(
    address _RuliTokenAddress,
    address _fundAddress,
    uint256 _offeredAmount,
    uint _rate
    )
    Crowdsale(26900, 30000, _rate, _fundAddress)
    {
      token = RuliToken(_RuliTokenAddress);
      fund = _fundAddress;
      offeredAmount = _offeredAmount;
      rate = _rate;
  }
  function createTokenContract() internal returns (MintableToken) {
    return new RuliToken();
  }
}
