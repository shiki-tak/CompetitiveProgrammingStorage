pragma solidity ^0.4.15;

contract HelloWorld {
  string public greeting;

  function HelloWorld(string _greeting) {
    greeting = _greeting;
  }

  function setGreeting(string _greeting) returns (string){
    greeting = _greeting;
    return greeting;
  }

  function say() constant returns (string) {
    return greeting;
  }
}
