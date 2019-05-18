use client::redis_client;
use model::task::Task;

pub fn create(id: i32, title: String, content: String, deadline: i64) -> String {
    update(id, title, content, deadline)
}

pub fn update(id: i32, title: String, content: String, deadline: i64) -> String {
    let task = Task::new(id, title, content, deadline);
    let json = serde_json::to_string(&task).unwrap();
    // redis_client::set(id, json);
    json
}

pub fn get() {

}

pub fn gets() {

}

pub fn delete() {

}