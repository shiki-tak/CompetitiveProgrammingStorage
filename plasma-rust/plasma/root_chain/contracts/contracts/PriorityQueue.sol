pragma solidity ^0.5.0;

import "./SafeMath.sol";

contract PriorityQueue {
    using SafeMath for uint256;

    // Storage
    address owner;
    uint256[] heapList;
    uint256 public currentSize;

    // Modifiers
    modifier onlyOwner() {
        require(msg.sender == owner, "Sender must be owner.");
        _;
    }

    // Constructor
    constructor() public {
        owner = msg.sender;
        heapList = [0];
        currentSize = 0;
    }

    // Internal functions

    /**
     * Inserts an element into the priority queue.
     */
    function insert(uint256 _priority, uint256 _value) public onlyOwner {
        uint256 element = _priority << 128 | _value;
        heapList.push(element);
        currentSize = currentSize.add(1);
        _perUp(currentSize);
    }

    /**
     * Returns the top element of the heap.
     */
    function getMin() public view returns (uint256, uint256) {
        return _splitElement(heapList[1]);
    }

    /**
     * Deletes the top element of the heap and shifts everything up.
     */
    function delMin() public onlyOwner returns (uint256, uint256) {
        uint256 retVal = heapList[1];
        heapList[1] = heapList[currentSize];
        delete heapList[currentSize];
        currentSize = currentSize.sub(1);
        _perDown(1);
        heapList.length = heapList.length.sub(1);
        return _splitElement(retVal);
    }

    // Private function

    function _minChild(uint256 _index) private view returns (uint256) {
        if (_index.mul(2).add(1) > currentSize) {
            return _index.mul(2);
        } else {
            if (heapList[_index.mul(2)] < heapList[_index.mul(2).add(1)]) {
                return _index.mul(2);
            } else {
                return _index.mul(2).add(1);
            }
        }
    }

    // Private function
    function _perUp(uint256 _index) private {
        uint256 index = _index;
        uint256 j = index;
        uint256 newVal = heapList[index];
        while (newVal < heapList[index.div(2)]) {
            heapList[index] = heapList[index.div(2)];
            index = index.div(2);
        }
        if (index != j) heapList[index] = newVal;
    }

    function _perDown(uint256 _index) private {
        uint256 index = _index;
        uint256 j = index;
        uint256 newVal = heapList[index];
        uint256 mc = _minChild(index);
        while (mc <= currentSize && newVal > heapList[mc]) {
            heapList[index] = heapList[mc];
            index = mc;
            mc = _minChild(index);
        }
        if (index != j) heapList[index] = newVal;
    }

    function _splitElement(uint256 _element) private pure returns (uint256, uint256) {
        uint256 priority = _element >> 128;
        uint256 value = uint256(uint128(_element));
        return (priority, value);
    }
}
