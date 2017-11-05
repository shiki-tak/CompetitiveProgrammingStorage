pragma solidity ^0.4.15;

contract CrowdFunding {

  enum Status {
    Funding,
    Succeeded,
    Failed
  }

  struct Investor {
    address addr;
    uint amount;
  }

  address public owner;
  uint public numInvestors;
  uint public deadline;
  Status public status;
  bool public ended;
  uint public goalAmount;
  uint public totalAmount;
  mapping (uint => Investor) public investors;

  modifier onlyOwner() {
    require(msg.sender == owner);
    _;
  }

  function CrowdFunding(uint _duration, uint _goalAmount) {
    owner = msg.sender;

    deadline = now + _duration;

    goalAmount = _goalAmount;
    status = Status.Funding;
    ended = false;

    numInvestors = 0;
    totalAmount = 0;
  }

  function fund() public payable {
    require(!ended);

    Investor inv = investors[numInvestors++];
    inv.addr = msg.sender;
    inv.amount = msg.value;
    totalAmount += inv.amount;
  }

  function checkGoalReached() public onlyOwner {
    require(!ended);

    require(now >= deadline);

    if (totalAmount >= goalAmount) {
      status = Status.Succeeded;
      require(!owner.send(this.balance));
    } else {
      uint i = 0;
      status = Status.Failed;
      ended = true;

      while(i <= numInvestors) {
        require(!investors[i].addr.send(investors[i].amount));
        i++;
      }
    }
  }

  function kill() public onlyOwner {
    selfdestruct(owner);
  }
}
