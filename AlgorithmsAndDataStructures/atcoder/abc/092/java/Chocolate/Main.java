import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = Integer.parseInt(sc.next());
		int d = Integer.parseInt(sc.next());
		int x = Integer.parseInt(sc.next());

		int cnt = x;

		List<Integer> array = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			array.add(Integer.parseInt(sc.next()));
		}


		for (int a : array) {
			for (int i = 0; i <= d; i++) {
				if (a * i + 1 <= d) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}
