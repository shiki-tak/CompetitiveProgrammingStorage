#[derive(Debug)]
pub struct Node {
    pub data: Vec<u8>,
    pub left: String,
    pub right: String,
}

impl Node {
    pub fn new(data: Vec<u8>, left: String, right: String) -> Self {
        Self { data: data, left: left, right: right }
    }
}