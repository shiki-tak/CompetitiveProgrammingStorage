package consensus

import (
	"crypto/sha256"
	"encoding/hex"
	"strconv"
	"time"
)

// Calculate the hash
func calcHash(previousHash string, merkleRoot string, nonce string, timeStamp int64) string {

	result := sha256.Sum256([]byte(previousHash + merkleRoot + nonce + strconv.FormatInt(timeStamp, 10)))
	resultAsString := hex.EncodeToString(result[:])
	return resultAsString

}

// Execute Proof Of Work
func Pow(previousHash string, merkleRoot string) (string, int, int64) {
	target := "00000"
	var blockHash string
	var timeStamp int64
	nonce := 0

	for {
		timeStamp = time.Now().Unix()
		calcResult := calcHash(previousHash, merkleRoot, string(nonce), timeStamp)
		matchTargetCondition := func(target string, calcResult string) bool {
			if target == calcResult[:len(target)] {
				return true
			}
			return false
		}
		if matchTargetCondition(target, calcResult) == true {
			blockHash = calcResult
			break
		}
		nonce++
	}
	return blockHash, nonce, timeStamp
}
