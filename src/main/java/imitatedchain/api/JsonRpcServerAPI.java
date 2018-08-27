package imitatedchain.api;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;

@JsonRpcService("/jsonrpc")
public interface JsonRpcServerAPI {
	String getBalance(@JsonRpcParam(value = "address") String address);
}
