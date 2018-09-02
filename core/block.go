package core

// Block Struct
type Block struct {
	Height       int
	PreviousHash string
	BlockHash    string
	MerkleRoot   string
	Nonce        int
	TimeStamp    int64
}
