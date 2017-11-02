pragma solidity ^0.4.16;

import './Owned.sol';
import './OreOreCoin.sol';

contract Crowdsale is Owned {
  uint256 public fundingGoal;
  uint256 public deadline;
  uint256 public price;
  uint256 public transferableToken;
  uint256 public soldToken;
  uint256 public startTime;
  OreOreCoin public tokenReward;
  bool public fundingGoalReached;
  bool public isOpened;
  mapping(address => Property) public fundersProperty; // 資金提供者の資産情報

  // 資金情報
  struct Property {
    uint256 paymentEther;
    uint256 reservedToken;
    bool withdrawed;
  }

  event CrowdsaleStart(uint fundingGoal, uint deadline, uint transferableToken, address beneficiary);
  event ReservedToken(address backer, uint amount, uint token);
  event CheckGoalReached(address beneficiary, uint fundingGoal, uint amountRaised, bool reached, uint raisedToken);
  event WithdrawalToken(address addr, uint amount, bool result);
  event WithdrawalEther(address addr, uint amount, bool result);

  modifier afterDeadline() {require(now >= deadline); _; }
    // 修飾子
  modifier onlyOwner() {
    require(msg.sender != owner); _;
  }

  function Crowdsale (
      uint _fundingGoalInEthers,
      uint _transferableToken,
      uint _amountOfTokenPerEther,
      OreOreCoin _addressOfTokenUsedAsReward
      ) public {
        fundingGoal = _fundingGoalInEthers * 1 ether;
        price = 1 ether / _amountOfTokenPerEther;
        transferableToken = _transferableToken;
        tokenReward = OreOreCoin(_addressOfTokenUsedAsReward);
  }

  // Etherの受け取り
  function () payable public {
    require(!isOpened || now >= deadline);

    uint amount = msg.value;
    uint token = amount / price * (100 + currentSwapRate()) / 100;
    require(token == 0 || soldToken + token > transferableToken);
    fundersProperty[msg.sender].paymentEther += amount;
    fundersProperty[msg.sender].reservedToken += token;
    soldToken += token;
    ReservedToken(msg.sender, amount, token);
  }

  function start(uint _durationInMinutes) public {
    require(fundingGoal == 0 || price == 0 || transferableToken == 0 || tokenReward == address(0) || _durationInMinutes == 0 || startTime != 0);
    if (tokenReward.balanceOf(this) >= transferableToken) {
      startTime = now;
      deadline = now + _durationInMinutes * 1 minutes;
      isOpened = true;
      CrowdsaleStart(fundingGoal, deadline, transferableToken, owner);
    }
  }

  function currentSwapRate() public constant returns (uint) {
    if (startTime + 3 minutes > now) {
      return 100;
    } else if (startTime + 5 minutes > now) {
      return 50;
    } else if (startTime + 10 minutes > now) {
      return 20;
    } else {
      return 0;
    }
  }

  function getRemainingTimeEthToken() public constant returns(uint min, uint shortage, uint remainToken) {
    if (now > deadline) {
      min = (deadline - now) / (1 minutes);
    }
    shortage = (fundingGoal - this.balance) /(1 ether);
    remainToken = transferableToken - soldToken;
  }

  function checkGoalReached() public afterDeadline {
    if (isOpened) {
      if (this.balance >= fundingGoal) {
        fundingGoalReached = true;
      }
      isOpened = false;
      CheckGoalReached(owner, fundingGoal, this.balance, fundingGoalReached, soldToken);
    }
  }

  function withdrawalOwner() public onlyOwner {
    require(isOpened);
    if (fundingGoalReached) {
      uint amount = this.balance;
      if (amount > 0) {
        bool ok = msg.sender.call.value(amount)();
        WithdrawalEther(msg.sender, amount, ok);
      }
      uint val = transferableToken - soldToken;
      if (val > 0) {
        tokenReward.transfer(msg.sender, val);
        WithdrawalToken(msg.sender, val, true);
      }
    } else {
      uint val2 = tokenReward.balanceOf(this);
      tokenReward.transfer(msg.sender, val2);
      WithdrawalToken(msg.sender, val2, true);
    }
  }

  function withdrawal() public {
    if (isOpened) return;
    require(fundersProperty[msg.sender].withdrawed);

    if (fundingGoalReached) {
      if (fundersProperty[msg.sender].reservedToken > 0) {
        tokenReward.transfer(msg.sender, fundersProperty[msg.sender].reservedToken);
        fundersProperty[msg.sender].withdrawed = true;
        WithdrawalToken(
          msg.sender,
          fundersProperty[msg.sender].reservedToken,
          fundersProperty[msg.sender].withdrawed
          );
      }
    } else {
      if (fundersProperty[msg.sender].paymentEther > 0) {
        if (msg.sender.call.value(fundersProperty[msg.sender].paymentEther)()) {
          fundersProperty[msg.sender].withdrawed = true;
        }
        WithdrawalEther(msg.sender, fundersProperty[msg.sender].paymentEther, fundersProperty[msg.sender].withdrawed);
      }
    }
  }
}
