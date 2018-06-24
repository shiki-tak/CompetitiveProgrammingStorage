package consensus

import (
	"crypto/sha256"
	"encoding/hex"
	"strconv"
)

// Calculate the hash
func calcHash(previousHash string, merkleRoot string, nonce string, timeStamp int64) string {

	result := sha256.Sum256([]byte(previousHash + merkleRoot + nonce + strconv.FormatInt(timeStamp, 10)))
	resultAsString := hex.EncodeToString(result[:])
	return resultAsString

}

func matchTargetCondition(target string, calcResult string) bool {
	if target == calcResult[:len(target)] {
		return true
	}
	return false
}

// Execute Proof Of Work
func Pow(previousHash string, merkleRoot string, timeStamp int64) (string, int, int64) {
	target := "0000"
	var blockHash string
	nonce := 0

	for {
		calcResult := calcHash(previousHash, merkleRoot, string(nonce), timeStamp)
		if matchTargetCondition(target, calcResult) {
			blockHash = calcResult
			break
		}
		nonce++
	}
	return blockHash, nonce, timeStamp
}
