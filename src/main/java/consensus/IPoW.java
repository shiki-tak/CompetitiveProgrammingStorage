package consensus;

public interface IPoW {

	PoW ExecPoW(String PreviousHash, String MerkleRoot, String Nonce);
}
