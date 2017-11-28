pragma solidity ^0.4.15;

import "zeppelin/contracts/token/MintableToken.sol";

contract RuliToken is MintableToken {

  string public name = 'RuliToken';
  string public symbol = 'RULI';
  uint public decimals = 18;

  function RuliToken(

  )
  MintableToken() {
  }
}
