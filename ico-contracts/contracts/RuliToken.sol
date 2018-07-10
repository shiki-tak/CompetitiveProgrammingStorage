pragma solidity ^0.4.15;

import "zeppelin/contracts/token/MintableToken.sol";

contract RuliToken is MintableToken {

  string public constant name = 'RuliToken';
  string public constant symbol = 'RULI';
  uint public constant decimals = 18;

}
