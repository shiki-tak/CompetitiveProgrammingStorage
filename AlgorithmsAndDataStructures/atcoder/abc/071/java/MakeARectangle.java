import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MakeARectangle {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());
		List<Long> bars = new ArrayList<>();
		long h = 0;
		long w = 0;

		for (int i = 0; i < n; i++) {
			long x = Integer.parseInt(sc.next());
			bars.add(x);
		}

		Map<Object, Long> result = bars.stream().collect(
				Collectors.groupingBy(x -> x, Collectors.counting()));

		Object[] uniqueBars = bars.stream()
		.distinct().sorted(Comparator.reverseOrder()).toArray();

		for (Object x : uniqueBars) {
			if (result.get(x) >= 2 && h == 0) {
				h = (long) x;
			} else if (result.get(x) >= 2 && w == 0) {
				w = (long) x;
			}
		}

		System.out.println(h * w);
	}
}
