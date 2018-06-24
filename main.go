// Miner
package main

import (
	"crypto/sha256"
	"encoding/hex"
	"encoding/json"
	"fmt"
	"time"

	"github.com/GoBlockchain/consensus"
	"github.com/GoBlockchain/core"
)

var blockChain []core.Block

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

	block := core.Block{
		PreviousHash: previousHash,
		BlockHash:    blockHash,
		MerkleRoot:   merkleRoot,
		Nonce:        nonce,
		TimeStamp:    timeStamp,
	}

	blockChain = make([]core.Block, 0, 10)
	blockChain = append(blockChain, block)
	blockAsJSON, err := json.Marshal(block)
	if err != nil {
		fmt.Printf("error at creating genesis block: %v", err)
	}
	fmt.Printf("%s\n", blockAsJSON)
}

func main() {
	var merkleRoot string

	// TODO:
	// calculate merkle root

	// TODO:
	// add miner

	// i: block number
	for i := 0; i < 10; i++ {
		// Latest Block Height Index
		latestBlockIndex := len(blockChain) - 1

		if i == 0 {
			// Create genesis block
			createGenesisBlock(merkleRoot)

		} else {
			blockHash, nonce, timeStamp := consensus.Pow(blockChain[latestBlockIndex].PreviousHash, merkleRoot, time.Now().Unix())
			createBlock(
				blockChain[latestBlockIndex].BlockHash,
				blockHash,
				merkleRoot,
				nonce,
				timeStamp,
			)
		}
	}
}
