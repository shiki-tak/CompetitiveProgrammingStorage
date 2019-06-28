pragma solidity ^0.5.0;

contract HelloWorld {
    string value;

    event Set(address from, string value);
    event Get(address from);

    constructor() public {
        value = "value";
    }

    function set(string memory _value) public {
        value = _value;

        emit Set(msg.sender, _value);
    }

    function get() public returns (string memory) {
        emit Get(msg.sender);
        return value;
    }
}
