use chrono::{Utc, DateTime};

#[derive(Debug)]
pub struct Task {
    title: String,
    content: String,
    // TODO: change to enum
    // category: String,
    deadline: DateTime<Utc>,
    done: bool,
}

impl Task {
    pub fn new(title: String, content: String, deadline: DateTime<Utc>) -> Self {
        Self { title: title, content: content, deadline: deadline, done: false }
    }
}