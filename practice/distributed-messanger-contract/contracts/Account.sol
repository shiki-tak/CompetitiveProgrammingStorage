pragma solidity ^0.4.15;

import './lib/StringUtils.sol';

contract Account {
  uint256 public id;

  struct User {
    string name;
    bytes32 password;
    address owner;
  }

  event RegisterUser(uint id, string name, bytes32 password, address owner);
  event SignIn(bool flag, bytes32 certification_token);

  mapping(uint => User) private users;

  function accountRegister(string _name, string _strPassword){
    require(bytes(_name).length > 0 && bytes(_strPassword).length > 0);
    require(duplicatedCheckName(_name));
    address _owner = msg.sender;
    bytes32 _password = keccak256(_strPassword);
    if (register(_name, _password, _owner) == true) {
      RegisterUser(id, _name, _password, _owner);
      id++;
    }
  }

  function signIn(string _name, string _strPassword) constant returns (bool flag, bytes32 certification_token){
    require(bytes(_strPassword).length > 0);
    for (uint i = 0; i < id; i++) {
      if (StringUtils.equal(users[i].name,  _name) && users[i].password == keccak256(_strPassword)) {
        flag = true;
        /*
        ** session（もしくはcookie）として持たせて、messageの送信時に利用する
        ** （messageの署名に利用する）
        */
        certification_token = keccak256(_name, _strPassword);
        SignIn(flag, certification_token);
        return;
      }
    }
    flag = false;
    certification_token = '';
    SignIn(flag, certification_token);
  }

  // 開発用にquery methodを追加
  // 本番環境にdeployする時には不要
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
