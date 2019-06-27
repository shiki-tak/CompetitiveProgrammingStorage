struct Utxo {
    pub owner: String,
    pub amount: u128,
}

impl Utxo {
    pub fn new (owner: String, amount: u128) -> Self {
        Self { owner: owner, amount: amount }
    }
}