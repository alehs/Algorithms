package edu.algo.datastructures.priorityqueue;

/**
 * Priority queue provides access to elements in specific order base on their weights (Comparable Key)
 * @param <Key>
 */
public interface PQ<Key extends Comparable> {
	void insert(Key v);
	Key pop();
	boolean isEmpty();
}
