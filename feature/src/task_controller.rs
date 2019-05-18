use chrono::{Utc, DateTime};

use model::task::Task;

pub fn create(title: String, content: String, deadline: DateTime<Utc>) -> Task {
    update(title, content, deadline)
}

pub fn update(title: String, content: String, deadline: DateTime<Utc>) -> Task {
    Task::new(title, content, deadline)
}

pub fn get() {

}

pub fn gets() {

}

pub fn delete() {

}