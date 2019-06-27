use transaction::transaction::Transaction;

#[derive(Debug)]
pub struct FixedMerkle {
    pub depth: i32,
    pub transaction_set: Vec<Transaction>,
    pub hashed: bool,
    pub root: Vec<u8>,
}

impl FixedMerkle {
    pub fn new(depth: i32, leaves: Vec<Transaction>, hashed: bool) -> Self {
        let root: Vec<u8> = Vec::new();
        Self{ depth: depth, transaction_set: leaves, hashed: hashed , root: root }
    }
}