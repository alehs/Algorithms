package edu.algo.c2sat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Sat {

	List<Clause> clauses;
	Set<Integer> variables;

	Map<Integer, Set<Clause>> clausByVar;
	Map<Clause, Set<Integer>> varByClaus;

	public Sat(int size) {
		clauses = new ArrayList<>(size);
		variables = new HashSet<>(size);
		clausByVar = new HashMap<>(size);
		varByClaus = new HashMap<>(size);
	}

	public void add(Clause cl) {
		clauses.add(cl);

		int ix = Math.abs(Integer.valueOf(cl.x));
		int iy = Math.abs(Integer.valueOf(cl.y));

		variables.add(ix);
		variables.add(iy);

		mapValues(cl, ix);
		mapValues(cl, iy);
	}

	private void mapValues(Clause cl, int var) {
		mapCl(cl, var);
		mapVl(cl, var);
	}

	private void mapVl(Clause cl, int var) {
		Set<Integer> vls = varByClaus.get(cl);
		if (vls == null) {
			vls = new HashSet<>();
		}
		vls.add(var);
		varByClaus.put(cl, vls);
	}

	private void mapCl(Clause cl, int var) {
		Set<Clause> cls = clausByVar.get(var);
		if (cls == null) {
			cls = new HashSet<>();
		}
		cls.add(cl);
		clausByVar.put(var, cls);		
	}

	public int size() {
		return variables.size();
	}

	/**
	 * Removes variable and all conditions where it participates.
	 * @param var
	 */
	public void removeVar(int var) {
		// clear var-clauses mapping
		Set<Clause> cls = clausByVar.remove(var);
		for (Clause clause : cls) {
			// clear clause-var mapping
			clearClMap(clause);
		}
		//variables.remove(var);
	}

	private void clearClMap(Clause cl) {
		Set<Integer> vars = varByClaus.remove(cl);
		clearClsValMap(cl, vars);
		clauses.remove(cl);
	}

	private void clearClsValMap(Clause cl, Set<Integer> indexes) {
		for (Integer index : indexes) {
			if (clausByVar.containsKey(index)) {
				clausByVar.get(index).remove(cl);
			}
		}
	}
}
