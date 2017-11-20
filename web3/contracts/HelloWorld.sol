pragma solidity ^0.4.15;

contract HelloWorld {
  event ExecHello(address from, string operation);

  function Hello() constant returns (string) {
    ExecHello(msg.sender, "Exec Hello!");

    return "Hello, World!";
  }
}
