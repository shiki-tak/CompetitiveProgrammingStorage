extern crate redis;

use redis::Commands;
use std::collections::HashMap;

use model::task::Task;

lazy_static! {
    pub static ref PREFIX: HashMap<u32, &'static str> = {
        let mut m = HashMap::new();
        m.insert(0, "t_");
        // TODO: add user prefix
        m
    };
}

pub fn set(id: i32, task: &Task) {
    let client = redis::Client::open("redis://127.0.0.1:6379/").unwrap();
    let con = client.get_connection().unwrap();

    let key = PREFIX.get(&0).unwrap().to_string() + &id.to_string();
    let _ : () = con.set(key, serde_json::to_string(&task).unwrap()).unwrap();
}

pub fn get(id: i32) -> Task {
    let client = redis::Client::open("redis://127.0.0.1:6379/").unwrap();
    let con = client.get_connection().unwrap();

    let key = PREFIX.get(&0).unwrap().to_string() + &id.to_string();
    let res: String = con.get(key).unwrap();

    serde_json::from_str(&res).unwrap()
}

pub fn gets() {

}

pub fn delete() {

}