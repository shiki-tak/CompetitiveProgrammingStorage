package atcoder.abc.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NarrowRectanglesEasyTest {

	NarrowRectanglesEasy nre = new NarrowRectanglesEasy();

	@Test
	void testCalcMinDistance1() {
		assertEquals(1, nre.calcMinDistance(3, 2, 6));
	}

	@Test
	void testCalcMinDistance2() {
		assertEquals(0, nre.calcMinDistance(3, 1, 3));
	}

	@Test
	void testCalcMinDistance3() {
		assertEquals(4, nre.calcMinDistance(5, 10, 1));
	}
}
