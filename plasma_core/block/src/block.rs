use std::collections::HashMap;

use utils::signatures;
use merkle::fixed_merkle::FixedMerkle;

// TODO: create constants.rs
pub const NULL_SIG: &'static str = "00000000000000000000000000000000000000000000000000000000000000000";

#[derive(Debug)]
pub struct Block {
    pub transactoin_set: Vec<String>,
    pub number: i32,
    pub sig: String,
    pub spent_utxos: HashMap<String, i64>,
    pub hash: String,
    pub signer: String,
    pub merkle: FixedMerkle,
    pub root: String,
    pub is_deposit_block: bool,
    pub encoded: Vec<u8>,
}

impl Block {
    pub fn new(transactoin_set: Vec<String>) -> Self {
        let encoded: Vec<u8> = Vec::new();  // FIXME: use rlp module

        Self { transactoin_set: transactoin_set,
        number: 0,
        sig: NULL_SIG.to_string(),
        spent_utxos: HashMap::new(),
        hash: "".to_string(),
        signer: "".to_string(),
        merkle: FixedMerkle::new(),
        root: "".to_string(),
        is_deposit_block: false,
        encoded: encoded
        }
    }

    pub fn sign(key: String) -> String {
        signatures::sign(&key)
    }
}
