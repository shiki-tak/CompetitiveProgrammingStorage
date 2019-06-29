use client::client::Client;

fn main() {
    println!("Hello, Plasma Rust");

    let client = Client::new("http://localhost:9545");

    println!("{:?}", client);
}
