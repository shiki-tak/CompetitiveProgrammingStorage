pragma solidity ^0.4.15;

import '../lib/StringUtils.sol';

contract Account {
  uint256 public id;

  struct User {
    string name;
    bytes32 password;
    address owner;
  }

  event RegisterUser(uint id, string name, bytes32 password, address owner);

  mapping(uint => User) private users;

  function accountRegister(string _name, string _strPassword){
    require(bytes(_name).length > 0 && bytes(_strPassword).length > 0);
    require(duplicatedCheckName(_name));
    address _owner = msg.sender;
    bytes32 _password = keccak256(_strPassword);
    // contractの生成と同時にユーザ登録
    if (register(_name, _password, _owner) == true) {
      RegisterUser(id, _name, _password, _owner);
      id++;
    }
  }

  function query(uint _id) constant returns (string name, bytes32 password, address owner) {
    name = users[_id].name;
    password = users[_id].password;
    owner = users[_id].owner;
  }

  // Usernameの重複チェック
  function duplicatedCheckName(string _name) private constant returns (bool){
    for (uint i = 0; i < id; i++) {
      if (StringUtils.equal(users[i].name,  _name)) {
        return false;
      }
    }
    return true;
  }


  function register(string _name, bytes32 _password, address _owner) private constant returns (bool) {
    users[id].name = _name;
    users[id].password = _password;
    users[id].owner = _owner;
    return true;
  }
}
