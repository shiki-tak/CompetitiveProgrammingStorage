pragma solidity ^0.4.15;

import './Owned.sol';

contract GreetingContract is Owned {
  string public greet;
  address public sender;

  function GreetingContract
  (
    string _greet
    )
    Owned() {
    greet = _greet;
    sender =  msg.sender;
  }

  function say() constant returns (string, address) {
    sender = msg.sender;
    return (greet, sender);
  }

  // constantがないとコントラクト実行時にトランザクションが発生する
  function sayChange() returns (string, address) {
    sender = msg.sender;
    return (greet, sender);
  }

  function getSender() constant returns (address) {
    return sender;
  }

  function setGreet(string _greet) onlyOwner returns (string) {
    greet = _greet;
    return greet;
  }
}
