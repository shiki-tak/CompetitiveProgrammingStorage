import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());
		List<Integer> cards = new ArrayList<>();

		int alice = 0;
		int bob = 0;

		for (int i = 0; i < n; i++) {
			int x = Integer.parseInt(sc.next());
			cards.add(x);
		}

		 Collections.sort(cards, Comparator.reverseOrder());

		 for (int i = 0; i < n; i++) {

			 if (i % 2 == 0) {
				 alice += cards.get(i);
			 } else {
				 bob += cards.get(i);
			 }
		 }
		 
		 System.out.println(alice - bob);

	}
}
