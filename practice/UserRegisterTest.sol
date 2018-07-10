pragma solidity ^0.4.15;

/*
* Register user contract example
*/
contract UserRegisterTest {
  uint public id;

  event RegisterUser(uint id, string name, string email, bytes32 password, Role role);
  enum Role {
    Admin,
    User
  }
  struct User {
    string name;
    string email;
    bytes32 password;
    Role role;
  }

  mapping (uint => User) private users;

  function registerUser(string _name, string _email, string _str_password, Role _role) {
    require(bytes(_name).length > 0 && bytes(_email).length > 0);
    bytes32 _password = keccak256(_str_password);
    register(_name, _email, _password, _role);
    RegisterUser(id, _name, _email, _password, _role);
    id++;
  }

  function getUser(uint _id) constant returns (uint, string, string, bytes32, Role) {
    return (_id, users[_id].name, users[_id].email, users[_id].password, users[_id].role);
  }

  // nameとpassword情報から認証を行う関数
  function certification(string _name, string _password) constant returns (bool success, bytes32 _h_password) {
    uint _id = 0;
    require(bytes(_password).length > 0);
    bytes32 _h_name = keccak256(_name);
    _h_password = keccak256(_password);
    if (keccak256(users[_id].name) == _h_name && users[_id].password == _h_password) {
        success = true;
    }
  }

  function register(string _name, string _email, bytes32 _password, Role _role) private constant returns (bool) {
    users[id].name = _name;
    users[id].email = _email;
    users[id].password = _password;
    users[id].role = _role;
    return true;
  }
}
