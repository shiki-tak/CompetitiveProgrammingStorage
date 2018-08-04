import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());
		List<Integer> d = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			d.add(Integer.parseInt(sc.next()));
		}

		long ans = d.stream()
					.distinct()
					.count();

		System.out.println(ans);
	}
}
