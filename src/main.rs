use block::block::Block;

fn main() {
    println!("Hello, Plasma MVP implemented Rust!");
    let transactoin_set: Vec<String> = Vec::with_capacity(0);
    let block = Block::new(transactoin_set);

    println!("{:?}", block);
}
