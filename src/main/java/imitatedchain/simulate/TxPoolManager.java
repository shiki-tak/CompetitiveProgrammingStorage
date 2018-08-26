package imitatedchain.simulate;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// ClientThreadが発行したTxがPoolされるクラス
public class TxPoolManager {
    /*
    * Transaction Pool
    * 本来はtransaction feeを見て、取り出す優先度を決める？
    */
    private final BlockingQueue<Transaction> txPool = new LinkedBlockingQueue<Transaction>();

    // Transaction Poolからトランザクションを取り出す
    public Transaction getTransactionFromPool() {
        Transaction tx = null;
        try {
            tx = txPool.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tx;
    }

    // Transaction Poolにトランザクションをためる
    public void putTransactionInPool(Transaction tx) {
        try {
            txPool.put(tx);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}