package edu.algo.graphs.c2sat;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import edu.algo.Utils;

/**
 * 2-Sat problem loader.
 */
public class SatLoader {

	public static Sat load(String path, boolean preprocess) throws FileNotFoundException {
		Sat sat;

        try (Scanner scanner = Utils.openFile(path)) {
    		int size = Integer.valueOf(scanner.nextLine().trim());
    		sat = new Sat(size);

    		while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\s");

                Clause cl = new Clause(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));               
                sat.add(cl);
            }
        }

        Utils.print("loaded.");

        if (preprocess) {
        	preprocessing(sat);
        }

		return sat;
	}


	/**
	 * Pre-processing step: remove excess data.
	 *  - all variables included in only one condition (set var to true and remove condition as satisfied)
	 *  - all variables which need the same value to satisfy all of their conditions
	 */
	public static void preprocessing(Sat sat) {
		long startTime = System.currentTimeMillis();
		while(prepstep(sat)) {
			Utils.print("one more pre-processing run");
		}
		Utils.print("Pre-processing finished in " + (System.currentTimeMillis() - startTime)/1000 + "sec");
	}

	public static boolean prepstep(Sat sat) {
		boolean more = false;

		for (Iterator<Integer> it = sat.variables.iterator(); it.hasNext();) {
			Integer var = it.next();

			Set<Clause> clauses = sat.clausByVar.get(var);
			if (clauses.size() == 1) {
				sat.removeVar(var);
				it.remove();
				more = true;

			} else if (clauses.size() == 0) {
				sat.removeVar(var);
				it.remove();
				more = true;

			} else {
				boolean allTheSame = true; 
				boolean same = clauses.iterator().next().getVarSign(var);
				for (Clause clause : clauses) {
					if (same != clause.getVarSign(var)) {
						allTheSame = false;
					}
				}
				if (allTheSame) {
					sat.removeVar(var);
					it.remove();
					more = true;
				}
			}
		}

		return more;
	}
}
