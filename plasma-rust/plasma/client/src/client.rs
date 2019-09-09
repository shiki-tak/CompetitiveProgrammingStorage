use web3::contract::{Contract, Options};
use web3::{transports, Web3};
use ethereum_types::Address;
use ::web3::Transport;

#[derive(Debug)]
pub struct Client {
    pub web3: Web3<transports::Http>,
    pub root_chain_contract: web3::contract::Contract<transports::Http>,
}


impl Client {

    pub fn new(url: &str) -> Self {
        let (eloop, transport) = web3::transports::Http::new(url).unwrap();
        let web3 = web3::Web3::new(transport);

        let root_chain_contract = get_contract_at_address(&web3);

        Self { web3: web3, root_chain_contract: root_chain_contract }
    }
    // fn get_contract_data(&self, contract_name: String) {
    // }
}

pub fn get_contract_at_address(web3: &Web3<transports::Http>) -> web3::contract::Contract<transports::Http> {
    let address: Address = match "F12b5dd4EAD5F743C6BaA640B0216200e89B60Da".parse() {
        Ok(v) => v,
        Err(e) => panic!(e),
    };

    let contract_abi = include_str!("../../root_chain/contracts/build/contracts/RootChain.abi");
    let contract = Contract::from_json(
        web3.eth(),
        address,
        contract_abi.as_bytes(),
    ).unwrap();

    contract
}
