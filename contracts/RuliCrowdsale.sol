pragma solidity ^0.4.15;

import 'zeppelin/contracts/crowdsale/Crowdsale.sol';
import 'zeppelin/contracts/token/MintableToken.sol';
import './RuliToken.sol';

contract RuliCrowdsale is Crowdsale {

  function RuliCrowdsale(
    uint256 start,
    uint256 end,
    uint _rate,
    address _fundAddress
    )
    Crowdsale(start, end, _rate, _fundAddress)
    {
      token = createTokenContract();
  }
  function createTokenContract() internal returns (MintableToken) {
    return new RuliToken();
  }

  function forwardFunds() internal {
    wallet.transfer(msg.value);
  }
}
