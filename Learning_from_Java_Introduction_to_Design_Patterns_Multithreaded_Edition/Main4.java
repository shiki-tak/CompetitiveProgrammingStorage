public class Main4 {
  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      System.out.println("Good!");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
      }
    }
  }
}
