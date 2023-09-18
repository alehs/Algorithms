package edu.algo.graphs.travelsailsmanproblem;

import java.util.ArrayList;
import java.util.List;

public class TspMap {

	List<Point> points;

	public TspMap(List<Point> points) {
		this.points = points;
	}

	public TspMap(int size) {
		points = new ArrayList<>(size);
	}

	public void addPoint(double x, double y) {
		points.add(new Point(x, y));
	}

	/**
	 * Returns distance between two points.
	 * @param v1 from
	 * @param v2 to
	 * @return
	 */
	public double getDistance(int v1, int v2) {
		checkRange(v1);
		checkRange(v2);
		Point from = points.get(toIndex(v1));
		Point to = points.get(toIndex(v2));
		return Math.sqrt(
				Math.pow((from.x - to.x), 2.0d) +
				Math.pow((from.y - to.y), 2.0d)
				);
	}

	private int toIndex(int v) {
		return v;//-1;
	}

	private void checkRange(int v) {
		if (v > points.size()) throw new IllegalArgumentException("Illegal point.");
	}

	public static class Point {
		double x;
		double y;
		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "(" + x + "," + y + ")";
		}
	}

	public int size() {
		return points.size();
	}
}
