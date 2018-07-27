package atcoder.abc.java;

public class Restaurant {

	public int calcPrice(int x) {
		int res = 0;
		res = 800 * x - 200 * (x / 15);

		return res;
	}

}
