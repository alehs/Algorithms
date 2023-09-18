package edu.algo.graphs.c2sat;

public class Clause {
	int x;
	int y;
	
	public Clause(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean check(boolean bx, boolean by) {
		boolean aX = (x > 0) ? bx : !bx;
		boolean aY = (y > 0) ? by : !by;
		return aX || aY;
	}

	public int getIndexX() {
		return Math.abs(x);
	}
	
	public int getIndexY() {
		return Math.abs(y);
	}

	public boolean getVarSign(int var) {
		if (getIndexX() == var) {
			return x > 0;
		} else if (getIndexY() == var) {
			return y > 0;
		} else {
			throw new IllegalArgumentException(String.format("Clause %s does not have value %d", this.toString(), var));
		}
	}
	
	@Override
	public String toString() {
		return String.format("(%d;%d)", x, y);
	}
}
