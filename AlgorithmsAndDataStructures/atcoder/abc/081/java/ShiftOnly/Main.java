import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static boolean checkArray(List<Integer> a) {
		for (int x : a) {
			if (x % 2 != 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());
		List<Integer> array = new ArrayList<>();
		int cnt = 0;

		for (int i = 0; i < n; i++) {
			int x = Integer.parseInt(sc.next());
			array.add(x);
		}

		while(checkArray(array)) {
			int i = 0;
			for (int x : array) {
				array.set(i, x / 2);
				i++;
			}
			cnt++;
		}
		System.out.println(cnt);
	}
}
