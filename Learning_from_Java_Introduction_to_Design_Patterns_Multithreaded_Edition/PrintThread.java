public class PrintThread extends Thread {
  private String message;
  public PrintThread(String message) {
    this.message = message;
  }
  public void run() {
    for (int i = 0; i < 100; i++) {
      System.out.println(message);
    }
  }

}