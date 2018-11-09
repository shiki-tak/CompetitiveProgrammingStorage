import java.util.Scanner;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = Integer.parseInt(sc.next());
        int y = Integer.parseInt(sc.next());

        System.out.println(x + y / 2);
    } 
}