package imitatedchain.api;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;

@JsonRpcService("/jsonrpc/account")
public interface AccountServerAPI {
	String getBalance(@JsonRpcParam(value = "address") String address);
	String newAccount();
}
