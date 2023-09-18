package edu.algo.datastructures.heap;

/**
 * Heap sort is a comparison based sorting technique based on Binary Heap data structure.
 *
 * 1) start with a source data filled in a heap's array
 * 2) build an in-place max-heap out of it starting from bottom to the top
 * 	- starting from latest elements
 *  - take its parent and apply sink to it
 * 3) Repeatedly remove the root from the heap
 * 	- take the root aside (put into the array)
 * 	- take latest element form arr and put it into the root
 * 	- apply sink on it
 *
 */
public class HeapSort<Key extends Comparable> extends BinaryHeap<Key> {

	public HeapSort(Key[] source) {
		super(source.length + 1);
		for(int i = 1; i <= source.length; i++) {
			arr[i] = source[i-1];
		}
		this.size = source.length;
		sortInPlace();
	}

	private void sortInPlace() {
		int size = arr.length;
		for (int i = size/2; i >= 1; i--) {
			sink(i);
		}
	}

	public Key[] sort() {
		Key [] result = (Key[]) new Comparable[size];
		int i = 0;
		while (size > 1) {
			result[i] = arr[1];
			exchange(1, size--);
			sink(1);
			i++;
		}
		result[i] = arr[1];
		return result;
	}

}
