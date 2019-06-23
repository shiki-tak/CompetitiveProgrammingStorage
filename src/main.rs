use block::block::Block;
use transaction::transaction::Transaction;

fn main() {
    println!("Hello, Plasma MVP implemented Rust!");
    let transactoin_set: Vec<Transaction> = Vec::with_capacity(0);
    let block = Block::new(transactoin_set);

    println!("{:?}", block);
}
