use client::redis_client;
use model::task::Task;

pub fn create(id: i32, title: String, content: String, deadline: i64) -> Task {
    update(id, title, content, deadline)
}

pub fn update(id: i32, title: String, content: String, deadline: i64) -> Task {
    let task = Task::new(id, title, content, deadline);
    redis_client::set(id, &task);
    task
}

pub fn get() {

}

pub fn gets() {

}

pub fn delete() {

}