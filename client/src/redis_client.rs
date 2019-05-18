extern crate redis;
use redis::Commands;

use model::task::Task;

pub fn set(id: i32, task: &Task) {
    let client = redis::Client::open("redis://127.0.0.1:6379/").unwrap();
    let con = client.get_connection().unwrap();

    let _ : () = con.set(id, serde_json::to_string(&task).unwrap()).unwrap();
}

pub fn get() {

}

pub fn gets() {

}

pub fn delete() {

}