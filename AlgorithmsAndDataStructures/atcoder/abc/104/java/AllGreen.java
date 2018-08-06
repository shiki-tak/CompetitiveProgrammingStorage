import java.util.Scanner;

public class AllGreen {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int d = Integer.parseInt(sc.next());
		long g = Integer.parseInt(sc.next());

		long[] p = new long[11];
		long[] c = new long[11];

		int ans = Integer.MAX_VALUE;

		for (int i = 1; i <= d; i++) {
			p[i] = Integer.parseInt(sc.next());
			c[i] = Integer.parseInt(sc.next());
		}

		for (int i = d; i > 0; i--) {
			int cnt =  0;
			long score = 0;
			boolean ok = false;

			for (int j = i; j > 0; j--) {
				for (int k = 1; k <= p[j]; k++) {
					cnt++;
					score += 100 * j;
					if (k == p[j]) {
						score += c[j];
					}
					if (score >= g) {
						ok = true;
						break;
					}

				}
				if (ok) {
					break;
				}
			}
			if (ok) {
//				System.out.println(i);
//				System.out.print("cnt ");
//				System.out.println(cnt);
//				System.out.print("score ");
//				System.out.println(score);
				ans = Math.min(cnt, ans);
				continue;
			} else {
				break;
			}
		}
		System.out.println(ans);
	}
}
