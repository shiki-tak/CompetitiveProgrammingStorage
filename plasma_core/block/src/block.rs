use std::collections::HashMap;

use utils::signatures;
use merkle::fixed_merkle::FixedMerkle;
use transaction::transaction::Transaction;

// TODO: create constants.rs
pub const NULL_SIG: &'static str = "00000000000000000000000000000000000000000000000000000000000000000";

#[derive(Debug)]
pub struct Block {
    pub transactoin_set: Vec<Transaction>,
    pub number: i32,
    pub sig: Vec<u8>,
    pub spent_utxos: HashMap<String, i64>,
    pub hash: String,
    pub signer: String,
    pub merkle: FixedMerkle,
    pub root: String,
    pub is_deposit_block: bool,
    pub encoded: String,
}

impl Block {
    pub fn new(transactoin_set: Vec<Transaction>) -> Self {
        Self { transactoin_set: transactoin_set,
        number: 0,
        sig: NULL_SIG.to_string().as_bytes().to_vec(),
        spent_utxos: HashMap::new(),
        hash: "".to_string(),
        signer: "".to_string(),
        merkle: FixedMerkle::new(),
        root: "".to_string(),
        is_deposit_block: false,
        encoded: "".to_string()
        }
    }

    pub fn sign(&self, key: String) -> Vec<u8> {
        signatures::sign(&key)
    }

    pub fn add_transaction(&mut self, tx: Transaction) {
        self.transactoin_set.push(tx)

    }
}
