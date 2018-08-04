import java.util.Scanner;

public class Restaurant {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());
		System.out.println(800 * n - (n / 15) * 200);
	}
}
