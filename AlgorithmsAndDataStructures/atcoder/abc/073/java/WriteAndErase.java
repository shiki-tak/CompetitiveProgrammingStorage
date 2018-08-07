import java.util.*;

public class WriteAndErase {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());
		Map<Integer, Integer> numCount =new HashMap<>();

		for (int i = 0; i < n; i++) {
			int x = Integer.parseInt(sc.next());
			int count = numCount.containsKey(x) ? numCount.get(x) : 0;
			numCount.put(x, count + 1);
		}
		long ans = numCount.entrySet().stream().filter(s -> s.getValue() % 2 != 0).count();
		System.out.println(ans);
	}
}
