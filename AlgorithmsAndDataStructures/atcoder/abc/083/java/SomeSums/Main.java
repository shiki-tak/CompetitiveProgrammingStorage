import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());
		int a = Integer.parseInt(sc.next());
		int b = Integer.parseInt(sc.next());
		int res = 0;

		for (int i = 1; i <= n; i++) {
			int target = i;
			int x = 0;
			while(target > 0) {
				x += target % 10;
				target /= 10;
			}
			if (x >= a && x <= b) {
				res += i;
			}
		}
		System.out.println(res);
	}
}
