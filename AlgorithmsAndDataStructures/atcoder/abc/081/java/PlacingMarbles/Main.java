import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());
		int cnt = 0;

		while(n > 0) {
			int x = n % 10;
			if (x == 1) {
				cnt++;
			}
			n /= 10;
		}
		System.out.println(cnt);
	}
}
