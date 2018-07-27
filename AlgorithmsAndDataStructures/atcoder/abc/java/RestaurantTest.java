package atcoder.abc.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RestaurantTest {

	Restaurant restaurant = new Restaurant();

	@Test
	void testCalcPrice1() {
		assertEquals(15800, restaurant.calcPrice(20));
	}

	@Test
	void testCalcPrice2() {
		assertEquals(47200, restaurant.calcPrice(60));
	}
}
