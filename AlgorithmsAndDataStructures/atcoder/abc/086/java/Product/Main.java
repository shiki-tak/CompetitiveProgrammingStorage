import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = Integer.parseInt(sc.next());
		int b = Integer.parseInt(sc.next());

		String ans = a * b % 2 == 0 ? "Even" : "Odd";

		System.out.println(ans);
	}
}
