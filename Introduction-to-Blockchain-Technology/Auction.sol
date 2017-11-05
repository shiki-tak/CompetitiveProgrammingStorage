pragma solidity ^0.4.15;

contract Auction {
  address public highestBidder;
  uint public highestBid;

  function Auction() payable {
    highestBidder = msg.sender;
    highestBid = 0;
  }

  function bid() public payable {
    require(msg.value > highestBid);

    uint refundAmount = highestBid;

    address currentHighestBidder = highestBidder;

    highestBid = msg.value;
    highestBidder = msg.sender;

    require(!currentHighestBidder.send(refundAmount));
  }
}
