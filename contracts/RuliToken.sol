pragma solidity ^0.4.15;

import "tokens/HumanStandardToken.sol";

contract RuliToken is HumanStandardToken {
  function RuliToken(
    uint256 _initialAmount,
    string _tokenName,
    uint8 _decimal,
    string _tokenSymbol
    )
    HumanStandardToken(_initialAmount, _tokenName, _decimal, _tokenSymbol) {
      
    }
}
