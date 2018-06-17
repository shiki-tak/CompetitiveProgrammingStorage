public class Main2 {
  public static void main(String[] args) {
    new PrintThread("Good!").start();
    new PrintThread("Nice!").start();
  }
}
