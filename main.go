// Miner
package main

import (
	"encoding/json"
	"fmt"

	"github.com/GoBlockchain/consensus"
	"github.com/GoBlockchain/core"
)

// シュミレート
func main() {
	var merkleRoot string
	blockChain := core.Blockchain{}

	// TODO:
	// calculate merkle root

	// TODO:
	// add miner

	// i: ブロックを作る回数
	for i := 0; i < 10; i++ {
		// Latest Block Height Index
		latestBlockIndex := blockChain.GetLatestBlockIndex()

		if i == 0 {
			// Create genesis block
			blockChain.Blocks = append(blockChain.Blocks, blockChain.CreateGenesisBlock(merkleRoot))

		} else {
			// Proof Of Workを行い、ブロックを生成する
			blockHash, nonce, timeStamp := consensus.Pow(blockChain.Blocks[latestBlockIndex-1].PreviousHash, merkleRoot)

			// ナンス値が見つかったらブロックチェーンにブロックを追加する
			blockChain.Blocks = append(blockChain.Blocks, blockChain.CreateBlock(
				blockChain.Blocks[latestBlockIndex-1].BlockHash,
				blockHash,
				merkleRoot,
				nonce,
				timeStamp,
			))
		}
	}

	// 作成したブロックを出力
	for i := 0; i < 10; i++ {
		blockAsJSON, err := json.Marshal(blockChain.Blocks[i])
		if err != nil {
			fmt.Printf("Error when setting block to json: %v", err)
		}
		fmt.Printf("%s\n", blockAsJSON)
	}
}
