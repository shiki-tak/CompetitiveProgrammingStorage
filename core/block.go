package core

// Block Struct
type Block struct {
	PreviousHash string
	BlockHash    string
	MerkleRoot   string
	Nonce        int
	TimeStamp    int64
}
