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
		// 持っている棒
		List<Long> bars = new ArrayList<>();
		// 長方形の高さ
		long h = 0;
		// 長方形の幅
		long w = 0;

		for (int i = 0; i < n; i++) {
			long x = Integer.parseInt(sc.next());
			bars.add(x);
		}

		// 各長さの棒がいくつあるかカウントして、Mapに入れる
		Map<Object, Long> result = bars.stream().collect(
				Collectors.groupingBy(x -> x, Collectors.counting()));

		// 重複している要素を除去して、降順に並べる
		Object[] uniqueBars = bars.stream()
		.distinct().sorted(Comparator.reverseOrder()).toArray();

		// 棒がいくつあるかによって、作れる長方形が変わる
		// 同じ長さの棒が4本以上 -> 正方形
		// 同じ長さの棒が2本以上、4本未満　-> 長方形
		for (Object x : uniqueBars) {
			// h,wがどちらも決まっていないときに、4本以上あれば、正方形になる
			if (result.get(x) >= 4 && (h == 0 && w == 0)) {
				h = (long) x;
				w = (long) x;
				break;
			}
			// それ以外
			else if (result.get(x) >= 2 && h == 0) {
				h = (long) x;
				continue;
			} else if (result.get(x) >= 2 && w == 0) {
				w = (long) x;
				continue;
			}
		}

		System.out.println(h * w);
	}
}
