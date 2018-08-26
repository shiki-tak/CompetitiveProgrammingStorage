package imitatedchain.simulate;

public class Transaction {
    private final String txId;
    private final String from;

    Transaction(String txId, String from) {
        this.txId = txId;
        this.from = from;
    }
}