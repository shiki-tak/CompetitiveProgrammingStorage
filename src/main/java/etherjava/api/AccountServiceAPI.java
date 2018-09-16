package etherjava.api;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;

@JsonRpcService("/account")
public interface AccountServiceAPI {
	String getBalance(@JsonRpcParam(value = "address") String address);
	String newAccount(@JsonRpcParam(value = "balance") String balance);
}
