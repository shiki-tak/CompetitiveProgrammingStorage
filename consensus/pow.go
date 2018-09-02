package consensus

import (
	"crypto/sha256"
	"encoding/hex"
	"strconv"
	"time"
)

// Calculate the hash
func calcHash(previousHash string, merkleRoot string, nonce string, timeStamp int64) string {

	// sha256でハッシュ値計算を行う
	result := sha256.Sum256([]byte(previousHash + merkleRoot + nonce + strconv.FormatInt(timeStamp, 10)))
	// 文字列に変換する
	resultAsString := hex.EncodeToString(result[:])
	return resultAsString

}

// Execute Proof Of Work
func Pow(previousHash string, merkleRoot string) (string, int, int64) {
	target := "0000"
	var blockHash string
	var timeStamp int64
	nonce := 0

	for {
		timeStamp = time.Now().Unix()
		// ハッシュ値計算を行う
		calcResult := calcHash(previousHash, merkleRoot, string(nonce), timeStamp)

		// 計算したハッシュ値とtargetが一致するかチェックする。一致すれば、trueを返す
		matchTargetCondition := func(target string, calcResult string) bool {
			if target == calcResult[:len(target)] {
				return true
			}
			return false
		}

		// もし、matchTargetConditionがtrueならば、calcResultの結果をblockHashとして、PoWを終える
		if matchTargetCondition(target, calcResult) == true {
			blockHash = calcResult
			break
		}
		// もし、matchTargetConditionがfalseならば、nonceを変えて再計算する
		nonce++
	}
	return blockHash, nonce, timeStamp
}
