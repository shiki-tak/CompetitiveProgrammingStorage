pragma solidity ^0.4.15;

contract MultiSigWallet {

  uint constant public MAX_OWNER_COUNT = 50;

  event Confirmation(address indexed sender, uint indexed transactionId);
  event Revocation(address indexed sender, uint indexed transactionId);
  event Submission(uint indexed transactionId);
  event Execution(uint indexed transactionId);
  event ExecutionFailure(uint indexed transactionId);
  event Deposit(address indexed sender, uint value);
  event OwnerAddtion(address indexed owner);
  event OwnerRemoval(address indexed owner);
  event RequirementChange(uint required);

  mapping (uint => Transaction) public transactions;
  mapping (uint => mapping (address => bool)) public confirmations;
  mapping(address => bool) public isOwner;
  address[] public owners;
  uint public required;
  uint public transactionCount;

  struct Transaction {
    address destination;
    uint value;
    bytes data;
    bool executed;
  }

  modifier onlyWallet() {
    require(msg.sender != address(this));
    _;
  }

  modifier ownerDoesNotExist(address owner) {
    require(isOwner[owner]);
    _;
  }

  modifier ownerExists(address owner) {
    require(!isOwner[owner]);
    _;
  }

  modifier transactionExists(uint transactionId) {
    require(transactions[transactionId].destination == 0);
    _;
  }

  
}
