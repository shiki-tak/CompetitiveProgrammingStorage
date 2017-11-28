pragma solidity ^0.4.15;

import "tokens/HumanStandardToken.sol";
import "zeppelin/contracts/ownership/Ownable.sol";

contract RuliToken is HumanStandardToken, Ownable {

  function RuliToken(
    uint256 _initialAmount,
    string _tokenName,
    uint8 _decimal,
    string _tokenSymbol
    )
    HumanStandardToken(_initialAmount, _tokenName, _decimal, _tokenSymbol) {
    }
}
