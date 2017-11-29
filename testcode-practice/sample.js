var chai = require('chai');
var assert = chai.assert;

var myFunc = function(a, b) {
  return a + b;
};

describe('SAMPLE TEST', function() {
  it("myFunc's test1", function() {
    assert.strictEqual(myFunc(1, 2),3)
  });
  var result = myFunc(3, 3);
  it("myFunc's test2", function() {
    assert.strictEqual(result, 6)
  });
});
