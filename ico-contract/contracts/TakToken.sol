pragma solidity ^0.4.15;

import 'zeppelin/contracts/token/MintableToken.sol';

contract TakToken is MintableToken {

  string public constant name = 'TakToken';

  string public constant symbol = 'TAK';

  uint public constant decimals = 18;
}
