use chrono::{Utc, DateTime};

#[derive(Debug)]
pub struct Task {
    id: i32,
    title: String,
    content: String,
    // TODO: change to enum
    // category: String,
    deadline: DateTime<Utc>,
    done: bool,
}

impl Task {
    pub fn new(id: i32, title: String, content: String, deadline: DateTime<Utc>) -> Self {
        Self { id: id, title: title, content: content, deadline: deadline, done: false }
    }
}