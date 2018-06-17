public class WaitAndNotifyMain {
  public static void main(String[] args) {
    WaitAndNotify waitAndNotify = new WaitAndNotify();

    Runnable taskWait = waitAndNotify::threadWait;
    new Thread(taskWait).start();

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
    }

    Runnable taskNotify = waitAndNotify::threadNotify;
    new Thread(taskNotify).start();
  }
}
