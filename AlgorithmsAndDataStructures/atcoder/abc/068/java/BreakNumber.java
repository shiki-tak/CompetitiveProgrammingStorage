import java.util.Scanner;

public class BreakNumber {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());
		int ans = 0;
		int ansCandidate[] = {1, 2, 4, 8, 16, 32, 64};
        
		for (int i = 0; i < 7; i++) {
			if (ansCandidate[i] <= n) {
				ans = ansCandidate[i];
			}
		}
		System.out.println(ans);
	}
}
