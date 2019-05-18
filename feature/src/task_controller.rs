use chrono::{Utc, DateTime};

use model::task::Task;

pub fn create(id: i32, title: String, content: String, deadline: DateTime<Utc>) -> Task {
    update(id, title, content, deadline)
}

pub fn update(id: i32, title: String, content: String, deadline: DateTime<Utc>) -> Task {
    Task::new(id, title, content, deadline)
}

pub fn get() {

}

pub fn gets() {

}

pub fn delete() {

}