package edu.algo.graphs.c2sat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
