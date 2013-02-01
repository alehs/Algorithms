package edu.algo.c2sat;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class ClauseTest {

	@Test
	public void testCheck() {
		assertFalse(new Clause(-1, -2).check(true, true));
		assertTrue(new Clause(-1, -2).check(true, false));
		assertTrue(new Clause(-1, -2).check(false, true));
		assertTrue(new Clause(-1, -2).check(false, false));

		assertFalse(new Clause(1, 2).check(false, false));
		assertTrue(new Clause(1, 2).check(false, true));
		assertTrue(new Clause(1, 2).check(true, false));
		assertTrue(new Clause(1, 2).check(true, true));
	}

}
