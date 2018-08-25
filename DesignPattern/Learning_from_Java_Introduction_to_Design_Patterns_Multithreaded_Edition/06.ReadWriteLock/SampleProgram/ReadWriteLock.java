public final class ReadWriteLock {
    private int readingReaders = 0;         // 実際に読んでいる最中のスレッド数
    private int waitingWriters = 0;         // 書くのを待っているスレッドの数
    private int writingWriters = 0;         // 実際に書いている最中のスレッド数
    private boolean preferWriter = true;    // 書くのを優先するならtrue

    public synchronized void readLock() throws InterruptedException {
        while (writingWriters > 0 || (preferWriter && waitingWriters >0)) {
            wait();
        }
        readingReaders++;                       // 実際に読んでいるスレッドの数を1増やす
    }

    public synchronized void readUnlock() {
        readingReaders--;                       // 実際に読んでいるスレッドの数を1減らす
        preferWriter = true;
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        waitingWriters++;                       // 書くのを待っているスレッドの数を1増やす
        try {
            while (readingReaders > 0 || writingWriters > 0) {
                wait();
            }
        } finally {
            waitingWriters--;                   // 書くのを待っているスレッドの数を1減らす
        }
        writingWriters++;                       // 実際に書いているすｒッドの数を1増やす
    }

    public synchronized void writeUnlock() {
        writingWriters--;                       // 実際に書いているスレッドの数を1減らす
        preferWriter = false;
        notifyAll();
    }
}