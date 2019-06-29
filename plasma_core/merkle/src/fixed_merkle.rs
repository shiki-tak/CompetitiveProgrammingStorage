use std::i32;
use ethereum_rust_utils;

use crate::node::Node;

#[derive(Debug)]
pub struct FixedMerkle {
    pub depth: u32,
    pub leaf_count: i32,
    pub hashed: bool,
    pub leaves: Vec<Vec<u8>>,
    pub root: Vec<u8>,
    pub tree: Vec<Node>,
}

impl FixedMerkle {
    // TODO: implement constructor
    pub fn new(depth: u32, leaves: Vec<Vec<u8>>, hashed: bool) -> Self {
        let leaf_count = 2_i32.pow(depth);
        let tree: Vec<Node> = Vec::new();

        let root: Vec<u8> = Vec::new();
        Self{ depth: depth, leaf_count: leaf_count,
            hashed: hashed , leaves: leaves, root: root,
            tree: tree }
    }

    // TODO: implement
    pub fn create_nodes(&self, leaves: Vec<u8>) -> Vec<Node> {
        let nodes: Vec<Node> = Vec::new();
        nodes
    }

    pub fn create_tree(&self, leaves: Vec<String>) {
    }

    // TODO: implement
    pub fn check_membership(&self) {

    }

    // TODO: implement
    pub fn create_membership_proof(&self, leaf: Node) -> Vec<u8> {
        "dummy_proof".as_bytes().to_vec()
    }

    // TODO: implement
    fn is_member(&self, leaf: Node) -> bool {
        true
    }

    // TODO: implement
    fn not_member(&self, leaf: Node) -> bool {
        true
    }
}
