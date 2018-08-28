package imitatedchain.simulate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

public class ClientThread extends Thread {

    private final Blockchain blockChain;
    private final TxPoolManager txPoolManager;

    ClientThread(String name, Blockchain blockChain, TxPoolManager txPoolManager) {
        super(name);
        this.blockChain = blockChain;
        this.txPoolManager = txPoolManager;
    }

    @Override
	public void run() {
        try {
            // exec sendTransaction
            while(true) {
                Random rand = new Random();
                int seed = rand.nextInt(1000);

                Transaction transaction = sendTransaction(Thread.currentThread().getName(), seed);
                txPoolManager.putTransactionInPool(transaction);
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private Transaction sendTransaction(String name, int seed) throws NoSuchAlgorithmException {
        String txHashSeed = name + String.valueOf(seed);

        // トランザクションのハッシュ値を計算
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(txHashSeed.getBytes());
        byte[] txHashAsBytes = md.digest();
        String txHash = DatatypeConverter.printHexBinary(txHashAsBytes);

        Transaction transaction = new Transaction(txHash, name);

        return transaction;
    }
}