use rlp;
use ethereum_rust_utils;

use utils::signatures;

// TODO: create constants.rs
pub const NULL_SIG: &'static str = "00000000000000000000000000000000000000000000000000000000000000000";
pub const KECCAK256_BYTES: usize = 32;

#[derive(Debug, Clone)]
pub struct Transaction {
    pub blk_num1: u128,
    pub tx_index1: u128,
    pub output_index1: u128,
    pub blk_num2: u128,
    pub tx_index2: u128,
    pub output_index2: u128,
    pub cur12: String,
    pub new_owner1: String,
    pub amount1: u128,
    pub new_owner2: String,
    pub amount2: u128,
    pub sig1: Vec<u8>,
    pub sig2: Vec<u8>,
    // pub confirmation1: ,
    // pub confirmation2: ,
    pub spent1: bool,
    pub spent2: bool
}

impl Transaction {
    pub fn new (blk_num1: u128, tx_index1: u128, output_index1: u128,
                blk_num2: u128, tx_index2: u128, output_index2: u128,
                cur12: String,
                new_owner1: String, amount1: u128,
                new_owner2: String, amount2: u128
                ) -> Self {
        Self { blk_num1: blk_num1, tx_index1: tx_index1, output_index1: output_index1,
            blk_num2: blk_num2, tx_index2: tx_index2, output_index2: output_index2,
            cur12: cur12,
            new_owner1: new_owner1, amount1: amount1,
            new_owner2: new_owner2, amount2: amount2,
            sig1: NULL_SIG.to_string().as_bytes().to_vec(),
            sig2: NULL_SIG.to_string().as_bytes().to_vec(),
            spent1: false,
            spent2: false,
            }
    }

    pub fn hash(&self) -> Vec<u8> {
        ethereum_rust_utils::keccak256(&self.encoded()).to_vec()
    }

    pub fn merkle_hash(&mut self) -> Vec<u8> {
        let mut target: Vec<u8> = Vec::new();
        target.append(&mut self.hash().to_vec());
        target.append(&mut self.sig1);
        target.append(&mut self.sig2);

        ethereum_rust_utils::keccak256(&target).to_vec()
    }

    pub fn is_single_utxo(&self) -> bool {
        self.blk_num2 == 1
    }

    pub fn is_deposit_transaction(&self) -> bool {
        self.blk_num1 == 0 && self.blk_num2 == 0
    }

    pub fn sender1(&self) -> Vec<u8> {
        signatures::get_signer(self.hash().to_vec(), self.sig1.clone()) // TODO: Is it correct to use clone?
    }

    pub fn sender2(&self) -> Vec<u8> {
        signatures::get_signer(self.hash().to_vec(), self.sig2.clone())
    }

    pub fn encoded(&self) -> Vec<u8> {
        let data = "data";
        rlp::encode(&data).to_vec() // FIXME: change encode target
    }

    pub fn sign1(&self, key: String) -> Vec<u8> {
        signatures::sign(&key)
    }

    pub fn sign2(&self, key: String) -> Vec<u8> {
        signatures::sign(&key)
    }
}
