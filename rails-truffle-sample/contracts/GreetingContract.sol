pragma solidity ^0.4.15;

contract GreetingContract {
  string public greet;

  function GreetingContract(string _greet) {
    greet = _greet;
  }

  function say() constant returns (string) {
    return greet;
  }

  function setGreet(string _greet) returns (string) {
    greet = _greet;
    return greet;
  }
}
