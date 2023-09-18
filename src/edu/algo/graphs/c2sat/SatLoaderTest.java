package edu.algo.graphs.c2sat;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SatLoaderTest {

	@Test
	public void testPreprocessing() {
		Sat sat = new Sat(4);
		sat.add(new Clause(1,2));
		sat.add(new Clause(-1,-2));
		Clause rm1 = new Clause(3,-2);
		Clause rm2 = new Clause(3,2);
		sat.add(rm1);
		sat.add(rm2);

		SatLoader.preprocessing(sat);

		assertEquals(2, sat.size());
		assertEquals(2, sat.clauses.size());

		assertFalse(sat.variables.contains(3));
		assertFalse(sat.clauses.contains(rm1));
		assertFalse(sat.clauses.contains(rm2));

		assertFalse(sat.clausByVar.containsKey(3));
		assertEquals(2, sat.clausByVar.get(2).size());

		assertFalse(sat.varByClaus.containsKey(rm1));
		assertFalse(sat.varByClaus.containsKey(rm2));
	}

	@Test
	public void testPreprocessing2() {
		Sat sat = new Sat(4);
		sat.add(new Clause(1,2));
		sat.add(new Clause(-1,2));
		sat.add(new Clause(3,1));

		SatLoader.preprocessing(sat);

		assertEquals(0, sat.clauses.size());
		assertEquals(0, sat.clausByVar.size());
		assertEquals(0, sat.varByClaus.size());
	}

	@Test
	public void testPreprocessing3() {
		Sat sat = new Sat(4);
		sat.add(new Clause(1,2));
		sat.add(new Clause(-1,2));
		sat.add(new Clause(-2,1));

		SatLoader.preprocessing(sat);

		assertEquals(3, sat.clauses.size());
		assertEquals(2, sat.clausByVar.size());
		assertEquals(3, sat.varByClaus.size());
	}
}
