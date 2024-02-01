package edu.algo.datastructures.heap;

/**
 * Binary Heap - array representation of a heap-ordered complete binary tree
 *  - keys in nodes
 *  - parent's key no smaller than children's keys (heap ordering)
 *
 * Binary tree - empty or node with links to left and right binary trees
 * Complete tree - perfectly balanced, except for bottom level=
 *
 * Properties:
 *  - Height of complete tree of N nodes - log N
 *  - Height only increases if N is a power of 2
 *
 *  Basic logic:
 *   - add new element to the end and swim it up.
 *   - remove element - exchange it with latest one and then sink it.
 */
public class BinaryHeap<Key extends Comparable> {

	/**
	 * indices start at 1
	 * keep nodes in level order:
	 *            A
	 *      B 			C
	 *   D     E     F
	 *
	 *  arr:
	 *  0, 1, 2, 3, 4, 5, 6
	 *  -, A, B, C, D, E, F
	 *
	 * Props:
	 *  largest key is a root.
	 *  children nodes of K are at 2K and 2K+1
	 */
	Key arr[];
	int size = 0;

	public BinaryHeap(int maxSize) {
		arr = (Key[]) new Comparable[maxSize];
	}

	/**
	 * If element's value is grated then it's parent, then exchange them.
	 * and continue back to the top.
	 *
	 * hint: parent node of K is K/2
	 */
	void swim(int k) {
		while (k > 1 && less(k/2, k)) {
			exchange(k, k/2);
			k = k/2;
		}
	}

	/**
	 * If element's value is less then it's children, then exchange them.
	 *
	 * hint: children nodes of K is K*2 and K*2+1
	 * @param k
	 */
	void sink(int k) {
		while (2*k <= size) {
			int ch = 2*k;  // left child of k
			if (ch < size && less(ch, ch+1)) ch++;  // if right child is bigger - take it.
			if (!less(k, ch)) break;
			exchange(k, ch);
			k = ch;
		}
	}

	/**
	 * Exchange root with last element, then sink it.
	 */
	public Key pop() {
		Key max = (Key)arr[1];
		exchange(1, size--);
		arr[size + 1] = null;
		sink(1);
		return max;
	}

	public void  insert(Key k) {
		arr[++size]	= k;
		swim(size);
	}

	void exchange(int i, int k) {
		Key temp = arr[i];
		arr[i] = arr[k];
		arr[k] = temp;
	}

	private boolean less(int a, int b) {
		return (arr[a]).compareTo(arr[b]) < 0;
	}
}
