// FIXME: use sign api in parity-common?
pub fn sign(key: &str) -> String {
    let mut result = String::with_capacity(key.len() + 7);
    result.push_str(key);
    result.push_str(" signed");
    result
}
