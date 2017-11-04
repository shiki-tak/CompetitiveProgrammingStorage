pragma solidity ^0.4.15;

import './lib/MultiSigWallet.sol';

contract TakFund is MultiSigWallet {

  function TakFund(address[] _owners, uint _required)
  public validRequirement(_owners.length, _required) MultiSigWallet(_owners, _required) {

  }
}
