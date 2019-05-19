extern crate redis;
use redis::Commands;

use model::task::Task;

pub fn set(key: i32, task: &Task) {
    let client = redis::Client::open("redis://127.0.0.1:6379/").unwrap();
    let con = client.get_connection().unwrap();

    let _ : () = con.set(key, serde_json::to_string(&task).unwrap()).unwrap();
}

pub fn get(key: i32) -> Task {
    let client = redis::Client::open("redis://127.0.0.1:6379/").unwrap();
    let con = client.get_connection().unwrap();

    let res: String = con.get(key).unwrap();

    serde_json::from_str(&res).unwrap()
}

pub fn gets() {

}

pub fn delete() {

}