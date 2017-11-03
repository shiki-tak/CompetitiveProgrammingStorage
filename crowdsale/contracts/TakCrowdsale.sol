pragma solidity ^0.4.15;

import 'zeppelin-solidity/contracts/crowdsale/CappedCrowdsale.sol';
import 'zeppelin-solidity/contracts/crowdsale/RefundableCrowdsale.sol';
import 'zeppelin-solidity/contracts/token/MintableToken.sol';
import 'zeppelin-solidity/contracts/lifecycle/Pausable.sol';
import './TakToken.sol';


contract TakCrowdsale is CappedCrowdsale, RefundableCrowdsale, Pausable {

}
