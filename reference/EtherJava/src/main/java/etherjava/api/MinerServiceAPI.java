package etherjava.api;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;

@JsonRpcService("/miner")
public interface MinerServiceAPI {

	String start(@JsonRpcParam(value = "address") String address);

	String stop();
}
