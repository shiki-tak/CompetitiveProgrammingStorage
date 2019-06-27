// FIXME: use sign api in parity-common?
pub fn sign(key: &str) -> Vec<u8> {
    let res = "dummy_result";
    res.as_bytes().to_vec()
}

pub fn get_signer(hash: Vec<u8>, sig: Vec<u8>) -> Vec<u8> {
    // FIXME: implement
    let v: Vec<u8> = Vec::new();
    v
}
