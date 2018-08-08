package consensus;

public class PoW implements IPoW {

	PoW PoWResult = new PoW();

	String Target = "0000";
	String BlockHash = "0x0";
	long TimeStamp = 0;
	int Nonce = 0;

	public boolean MatchTargetCondition(String Target, String CalcResult) {
		if (Target == CalcResult(Target)) {
			return true;
		}
		return false;
	}


	@Override
	public PoW CalcHash(String PreviousHash, String MerkleRoot, String Nonce, long TimeStamp) {


		while(true) {
			if (MatchTargetCondition(Target, CalcResult)) {

			}
		}
		return PoWResult;
	}

}
