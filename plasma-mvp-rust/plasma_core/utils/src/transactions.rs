pub const BLKNUM_OFFSET: u128 = 1000000000;
pub const TXINDEX_OFFSET: u128 = 10000;

pub fn encode_utxo_id(blk_num: u128, tx_index: u128, output_index: u128) -> u128 {
    ( blk_num * BLKNUM_OFFSET ) + (tx_index * TXINDEX_OFFSET) + (output_index * 1)
}