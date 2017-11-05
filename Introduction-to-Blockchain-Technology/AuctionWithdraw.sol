pragma solidity ^0.4.15;

contract AuctionWithdraw {
  address public highestBidder;
  uint public highestBid;
  mapping (address => uint) public usersBalances; // 返金額を管理するマップ

  function Auction() payable {
    highestBidder = msg.sender;
    highestBid = 0;
  }

  function bid() public payable {
    require(msg.value > highestBid);

    /*uint refundAmount = highestBid;*/
    /*address currentHighestBidder = highestBidder;*/

    // 最高額提示アドレスの返金額を更新する
    usersBalances[highestBidder] += highestBid;
    highestBid = msg.value;
    highestBidder = msg.sender;
  }

  // bid部分と返金部分を独立させる
  function withdraw() public {
    require(usersBalances[msg.sender] > 0);

    uint refundAmount = usersBalances[msg.sender];

    usersBalances[msg.sender] = 0;

    if (!msg.sender.send(refundAmount)) {
      throw;
    }
  }
}
