pragma solidity ^0.4.15;

contract UserContract {
  uint public id;
  address public owner;

  struct User {
    string name;
    bytes32 password;
  }

  event RegisterUser(uint id, string name, bytes32 password);

  mapping(uint => User) private users;
  modifier onlyOwner() {
    require(msg.sender != owner); _;
  }

  function UserContract(string _name, string _strPassword) constant returns (bool) {
    require(bytes(_name).length > 0 && bytes(_strPassword).length > 0);
    bytes32 _password = keccak256(_strPassword);
    // contractの生成と同時にユーザ登録
    if (register(_name, _password) == true) {
      RegisterUser(id, _name, _password);
      id++;
      return true;
    }
    return false;

  }


  function register(string _name, bytes32 _password) private constant returns (bool) {
    users[id].name = _name;
    users[id].password = _password;
    return true;
  }

}
