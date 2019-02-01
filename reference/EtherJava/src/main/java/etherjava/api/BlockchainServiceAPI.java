package etherjava.api;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;

@JsonRpcService("/blockchain")
public interface BlockchainServiceAPI {

	String createGenesisBlock();
	String getBlockByHeight(@JsonRpcParam(value = "height") String height);
	String getBlockByHash(@JsonRpcParam(value = "blockHash") String blockHash);
}
