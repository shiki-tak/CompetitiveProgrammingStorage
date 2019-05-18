extern crate redis;
use redis::Commands;

use model::task::Task;

pub fn set(id: i32, data: &Task) {
        let json = serde_json::to_string(&data).unwrap();

    // let client = redis::Client::open("redis://127.0.0.1/");
    // let con = client.get_connection();

    // let _ : () = con.set(id, task);

}

pub fn get() {

}