import java.util.Scanner;

public class SequentialSearch {

    public static boolean search(int[] a, int t) {
        boolean res = false;

        for (int x : a) {
            if (x == t) {
                res = true;
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = new int[10];

        for (int i = 0; i < 10; i++) {
            a[i] = i;
        }
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.next());
        
        System.out.println((search(a, t) ? "Yes" : "No"));
    }
}