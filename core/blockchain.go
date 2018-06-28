package core

import (
	"crypto/sha256"
	"encoding/hex"
	"encoding/json"
	"fmt"
	"time"
)

type Blockchain struct {
	Blocks []Block
}

var latestBlockIndex int

// Genesis Blockを作る
func (b *Blockchain) CreateGenesisBlock(merkleRoot string) Block {
	blockHashAsByte := sha256.Sum256([]byte("genesis block"))

	return b.CreateBlock(
		"0x0000000000000000000000000000000000000000000000000000000000000000",
		hex.EncodeToString(blockHashAsByte[:]),
		merkleRoot,
		42,
		time.Now().Unix(),
	)
}

// ブロックを作成する
func (b *Blockchain) CreateBlock(previousHash string, blockHash string, merkleRoot string, nonce int, timeStamp int64) Block {

	block := Block{
		Height:       latestBlockIndex,
		PreviousHash: previousHash,
		BlockHash:    blockHash,
		MerkleRoot:   merkleRoot,
		Nonce:        nonce,
		TimeStamp:    timeStamp,
	}

	latestBlockIndex++

	blockAsJSON, err := json.Marshal(block)
	if err != nil {
		fmt.Printf("error at creating genesis block: %v", err)
	}
	fmt.Printf("%s\n", blockAsJSON)
	return block
}

func (b *Blockchain) GetLatestBlockIndex() int {
	return latestBlockIndex
}
