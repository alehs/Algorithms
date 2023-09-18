package edu.algo.unionfind;

/**
 * Dynamic connectivity problem:
 *
 * given a set of N objects
 *  union command: connect two objects
 *  connected query: is there a path connecting the two objects
 */
public interface Union {

	void union(int p, int q); // add connection between p and q
	boolean connection(int p, int q); // are p and q connected?
//	int find(int p); // component identifier for p
//	int count(); // number of components

}
