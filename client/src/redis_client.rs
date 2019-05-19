extern crate redis;

use redis::{ Commands, Connection };
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
    let key = PREFIX.get(&0).unwrap().to_string() + &id.to_string();

    let _ : () = generate_client().set(key, serde_json::to_string(&task).unwrap()).unwrap();
}

pub fn get(id: i32) -> Task {
    let key = PREFIX.get(&0).unwrap().to_string() + &id.to_string();
    let res: String = generate_client().get(key).unwrap();

    serde_json::from_str(&res).unwrap()
}

pub fn gets() -> Vec<Task> {
    let con = generate_client();

    let key_pattern = PREFIX.get(&0).unwrap().to_string() + &"*".to_string();
    let mut tasks: Vec<Task> = Vec::new();
    // FIXME: use scan
    let task_keys: Vec<String> = generate_client().keys(key_pattern).unwrap();
    for key in task_keys {
        let task: String = con.get(key).unwrap();
        tasks.push(serde_json::from_str(&task).unwrap());
    }
    tasks
}

pub fn delete(id: i32) {
    let key = PREFIX.get(&0).unwrap().to_string() + &id.to_string();

    let _ : () = generate_client().del(key).unwrap();
}

fn generate_client() -> Connection {
    let client = redis::Client::open("redis://127.0.0.1:6379/").unwrap();
    client.get_connection().unwrap()
}
