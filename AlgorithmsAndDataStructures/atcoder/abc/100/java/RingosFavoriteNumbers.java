import java.util.Scanner;

public class RingosFavoriteNumbers {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int d = Integer.parseInt(sc.next());
		int n = Integer.parseInt(sc.next());

		if (n == 100) {
			n++;
		}

		long ans = (long) (Math.pow(100, d) * n);

		System.out.println(ans);
	}
}
