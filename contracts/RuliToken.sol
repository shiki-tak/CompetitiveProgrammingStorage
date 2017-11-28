pragma solidity ^0.4.15;

import "tokens/HumanStandardToken.sol";

contract RuliToken is HumanStandardToken {
  uint256 public offeredAmount;
  
  function RuliToken(
    uint256 _initialAmount,
    string _tokenName,
    uint8 _decimal,
    string _tokenSymbol,
    uint256 _offeredAmount
    )
    HumanStandardToken(_initialAmount, _tokenName, _decimal, _tokenSymbol) {
      offeredAmount = _offeredAmount;
    }
}
