public class Printer implements Runnable {
  private String message;
  public Printer(String message) {
    this.message = message;
  }
  public void run() {
    for (int i = 0; i < 100; i++) {
      System.out.println(message);
    }
  }
}
