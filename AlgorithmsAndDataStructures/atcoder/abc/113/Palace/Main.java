import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int t = Integer.parseInt(sc.next());
        double a = Double.parseDouble(sc.next());
        double minDiff = Integer.MAX_VALUE;
        int ans = 0;

        List<Integer> heightList = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
        	int x = Integer.parseInt(sc.next());
        	double ti = t - x * 0.006;
        	if (Math.abs(a - ti) < minDiff) {
                ans = i;
                minDiff = Math.abs(a - ti);
        	}
        }
        System.out.println(ans);
    }
}