package consensus;

public class PoWResult {
	public String blockHash;
	public long timeStamp;
	public int nonce;

	public PoWResult(String blockHash, int nonce, long timeStamp) {
		this.blockHash = blockHash;
		this.nonce = nonce;
		this.timeStamp = timeStamp;
	}
}
