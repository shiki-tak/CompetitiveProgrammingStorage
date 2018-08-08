package consensus;

public interface IPoW {

	PoW CalcHash(String PreviousHash, String MerkleRoot, String Nonce, long TimeStamp);
}
