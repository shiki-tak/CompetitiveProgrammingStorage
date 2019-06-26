use std::collections::HashMap;
use rlp;
use ethereum_rust_utils;

use utils::signatures;
use utils::transactions;
use merkle::fixed_merkle::FixedMerkle;
use transaction::transaction::Transaction;

// TODO: create constants.rs
pub const NULL_SIG: &'static str = "00000000000000000000000000000000000000000000000000000000000000000";

#[derive(Debug, Clone)]
pub struct Block {
    pub transaction_set: Vec<Transaction>,
    pub number: i32,
    pub sig: Vec<u8>,
    pub spent_utxos: HashMap<u128, bool>,
}

impl Block {
    pub fn new(transaction_set: Vec<Transaction>) -> Self {
        Self { transaction_set: transaction_set,
        number: 0,
        sig: NULL_SIG.to_string().as_bytes().to_vec(),
        spent_utxos: HashMap::new(),
        }
    }

    pub fn hash(&self) -> Vec<u8> {
        ethereum_rust_utils::keccak256(&self.encoded()).to_vec()
    }

    pub fn signer(&self) -> Vec<u8> {
        signatures::get_signer(self.hash().to_vec(), self.sig.clone()) // TODO: Is it correct to use clone?
    }

    pub fn merkle(&mut self) -> FixedMerkle {
        let mut hashed_transaction_set: Vec<Transaction> = Vec::new();
        hashed_transaction_set.append(&mut self.transaction_set);
        FixedMerkle::new(16, hashed_transaction_set, true)
    }

    pub fn root(&mut self) -> Vec<u8> {
        self.merkle().root
    }

    pub fn is_deposit_block(&self) -> bool {
        self.transaction_set.len() == 1 && self.transaction_set[0].is_deposit_transaction()
    }

    pub fn encoded(&self) -> Vec<u8> {
        let data = "data";
        rlp::encode(&data).to_vec() // FIXME: change encode target
    }

    pub fn sign(&self, key: String) -> Vec<u8> {
        signatures::sign(&key)
    }

    pub fn add_transaction(&mut self, tx: Transaction) {
        self.transaction_set.push(tx.clone());
        let inputs: [Vec<u128>; 2] = [vec![tx.blk_num1, tx.tx_index1, tx.output_index1],
                                        vec![tx.blk_num2, tx.tx_index2, tx.output_index2]];
        for i in 0..2 {
            let input_id = transactions::encode_utxo_id(inputs[i][0], inputs[i][1], inputs[i][2]);
            self.spent_utxos.insert(input_id, true);
        }
    }
}
