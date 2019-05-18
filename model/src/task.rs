#[derive(Debug, Serialize, Deserialize)]
pub struct Task {
    pub id: i32,
    pub title: String,
    pub content: String,
    // TODO: change to enum
    // category: String,
    pub deadline: i64,
    pub done: bool,
}

impl Task {
    pub fn new(id: i32, title: String, content: String, deadline: i64) -> Self {
        Self { id: id, title: title, content: content, deadline: deadline, done: false }
    }
}