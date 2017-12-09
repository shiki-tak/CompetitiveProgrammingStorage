//! Owned contract.
//! By Gav Wood (Ethcore), 2016.
//! Released under the Apache Licence 2.

pragma solidity ^0.4.15;

contract Owned {
  address public owner;

  event TransferOwnership(address oldaddr, address newaddr);

  modifier onlyOwner() {
    // bug fix
    require(msg.sender == owner);
    _;
  }

  function Owned() {
    owner = msg.sender;
  }

  function transferOwnership(address _new) onlyOwner {
    address oldaddr = owner;
    owner = _new;
    TransferOwnership(oldaddr, owner);
  }

  function getOwner() constant returns (address) {
    return owner;
  }
}
