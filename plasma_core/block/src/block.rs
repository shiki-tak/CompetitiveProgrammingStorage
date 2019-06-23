use std::collections::HashMap;

// FIXME: create constants.rs
pub const NULL_SIG: &'static str = "00000000000000000000000000000000000000000000000000000000000000000";

#[derive(Debug)]
pub struct Block {
    pub transactoin_set: Vec<String>,
    pub number: i32,
    pub sig: String,
    pub spent_utxos: HashMap<String, i64>
}

impl Block {
    pub fn new(transactoin_set: Vec<String>) -> Self {
        Self { transactoin_set: transactoin_set, number: 0, sig: NULL_SIG.to_string(), spent_utxos: HashMap::new() }
    }
}