package main

import (
	"crypto/sha256"
	"encoding/hex"
	"encoding/json"
	"fmt"
	"strconv"
	"time"
)

// Block Struct
type Block struct {
	PreviousHash string
	BlockHash    string
	MerkleRoot   string
	Nonce        int
	TimeStamp    int64
}

var blockChain []Block

func createGenesisBlock(merkleRoot string) {
	blockHashAsByte := sha256.Sum256([]byte("genesis block"))

	createBlock(
		"0x0000000000000000000000000000000000000000000000000000000000000000",
		hex.EncodeToString(blockHashAsByte[:]),
		merkleRoot,
		42,
		time.Now().Unix(),
	)
}

func createBlock(previousHash string, blockHash string, merkleRoot string, nonce int, timeStamp int64) {

	block := Block{
		PreviousHash: previousHash,
		BlockHash:    blockHash,
		MerkleRoot:   merkleRoot,
		Nonce:        nonce,
		TimeStamp:    timeStamp,
	}

	blockChain = make([]Block, 0, 10)
	blockChain = append(blockChain, block)
	blockAsJSON, err := json.Marshal(block)
	if err != nil {
		fmt.Printf("error at creating genesis block: %v", err)
	}
	fmt.Printf("%s\n", blockAsJSON)
}

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
func pow(merkleRoot string, timeStamp int64) bool {
	target := "0000"

	nonce := 0
	fmt.Println(string(nonce))

	// Latest Block Height Index
	i := len(blockChain) - 1

	for {
		calcResult := calcHash(blockChain[i].PreviousHash, merkleRoot, string(nonce), timeStamp)
		if matchTargetCondition(target, calcResult) {
			fmt.Println(calcResult)
			createBlock(
				blockChain[i].BlockHash,
				calcResult,
				merkleRoot,
				nonce,
				timeStamp,
			)
			break
		}
		nonce++
	}
	return true
}

func main() {

	var merkleRoot string

	// TODO:
	// calculate merkle root

	// TODO:
	// add miner

	// i: block number
	for i := 0; i < 10; i++ {
		if i == 0 {
			// Create genesis block
			createGenesisBlock(merkleRoot)

		} else {
			pow(merkleRoot, time.Now().Unix())
		}
	}
}
