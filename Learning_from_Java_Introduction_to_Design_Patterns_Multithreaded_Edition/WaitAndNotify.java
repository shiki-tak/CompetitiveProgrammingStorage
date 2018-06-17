public class WaitAndNotify {
  public synchronized void threadWait() {
    System.out.println("wait!");
    try {
      wait();
    } catch (InterruptedException e) {

    }
    System.out.println("unlocked!");
  }

  public synchronized void threadNotify() {
    notify();
    System.out.println("notify!");
  }
}
