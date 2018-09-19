package etherjava.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;

import etherjava.domain.service.miner.MinerService;

@Service
@AutoJsonRpcServiceImpl
public class MinerServiceAPIImpl implements MinerServiceAPI {

	@Autowired
	MinerService minerService;

	/*
	 * @param address coinbaseに相当
	 */
	@Override
	public String start(String address) {
		return minerService.miningStart(address);
	}

	@Override
	public String stop() {
		return minerService.miningStop();
	}
}
