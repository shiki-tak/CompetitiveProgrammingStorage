package atcoder.abc.java;

public class NarrowRectanglesEasy {

	public int calcMinDistance(int w, int a, int b) {
		int res = 0;

		if ((a <= b && a + w >= b) || (a <= b && b <= a + w)) {
			return res;
		} else {
			if (b + w < a) {
				res = a - (b + w);
			} else {
				res = b - (a + w);
			}
			return res;
		}
	}
}
