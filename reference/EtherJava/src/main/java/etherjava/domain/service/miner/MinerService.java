package etherjava.domain.service.miner;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import etherjava.domain.model.account.Account;
import etherjava.domain.service.account.AccountService;
import etherjava.domain.service.blockchain.BlockchainService;

@Service
public class MinerService {

	boolean miningFlag = false;

	@Autowired
	AccountService accountService;
	@Autowired
	SimulateBlockchain simulateBlockChain;
	@Autowired
	BlockchainService blockChainService;

	public String miningStart(String address) {
		Account account = accountService.findOne(address);
		if (account == null) {
			return "no such address";
		} else if (blockChainService.getblockIndex() == 0) {
			System.out.println(blockChainService.getblockIndex());
			return "Genesis block is not created...";
		} else {
			miningFlag = true;
			try {
				while (miningFlag) {
					// TODO: マジメにmining機能を実装する
					simulateBlockChain.createBlock();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "mining started!";
		}
	}

	public String miningStop() {
		miningFlag = false;
		return "mining stoped";
	}
}
