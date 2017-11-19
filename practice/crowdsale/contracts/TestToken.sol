pragma solidity ^0.4.15;

import "./lib/Owned.sol";
import "./Members.sol";

contract TestToken is Owned, Members {
  string public name;
  string public symbol;
  uint8 public decimales;
  uint256 public totalSupply;
  mapping (address => uint256) public balanceOf;
  mapping (address => int8) public blackList;
  mapping (address => Members) public members;

  event Transfer(address indexed from, address indexed to, uint256 value);
  event Blacklisted(address indexed target);
  event DeleteFromBlacklist(address indexed target);
  event RejectedPaymentToBlacklistedAddr(address indexed from, address indexed to, uint256 value);
  event RejectedPaymentFromBlacklistedAddr(address indexed from, address indexed to, uint256 value);
  event Cashback(address indexed from, address indexed to, uint256 value);

  function TestToken(uint256 _supply, string _name, string _symbol, uint8 _decimals) {
    balanceOf[msg.sender] = _supply;
    name = _name;
    symbol = _symbol;
    decimales = _decimals;
    totalSupply = _supply;
  }

  // register blacklist
  function blacklisting(address _addr) onlyOwner {
    blackList[_addr] = 1;
    Blacklisted(_addr);
  }

  // delete blacklist
  function deleteFromBlacklist(address _addr) onlyOwner {
    blackList[_addr] = -1;
    DeleteFromBlacklist(_addr);
  }

  function setMembers(Members _members) {
    members[msg.sender] = Members(_members);
  }

  function transfer(address _to, uint256 _value) {
    require(balanceOf[msg.sender] < _value);
    require(balanceOf[_to] + _value < balanceOf[_to]);

    if (blackList[msg.sender] > 0) {
      RejectedPaymentFromBlacklistedAddr(msg.sender, _to, _value);
    } else if (blackList[_to] > 0) {
      RejectedPaymentToBlacklistedAddr(msg.sender, _to, _value);
    } else {
      uint256 cashback = 0;
      if (members[_to] > address(0)) {
        cashback = _value / 100 * uint256(members[_to].getCashbackRate(msg.sender));
        members[_to].updateHistory(msg.sender, _value);
      }
      balanceOf[msg.sender] -= (_value - cashback);
      balanceOf[_to] += (_value - cashback);

      Transfer(msg.sender, _to, _value);
      Cashback(_to, msg.sender, cashback);
    }
  }




}
