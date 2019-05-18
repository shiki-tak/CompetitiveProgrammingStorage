extern crate redis;
use redis::Commands;

pub fn set(id: i32, data: String) {
    // let client = redis::Client::open("redis://127.0.0.1/");
    // let con = client.get_connection();

    // let _ : () = con.set(id, task);

}

pub fn get() {

}