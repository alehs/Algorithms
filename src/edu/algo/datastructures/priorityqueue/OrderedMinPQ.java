package edu.algo.datastructures.priorityqueue;

/**
 * Remove largest (or smallest) items from the queue
 */
public class OrderedMinPQ<Key extends Comparable> implements PQ<Key> {

	Key arr[];
	int size;

	public OrderedMinPQ(int maxSize) {
		arr = (Key[]) new Comparable[maxSize];
		size = 0;
	}

	@Override
	public void insert(Key v) {
		int position = findPosition(v);
		if (position < 0) return;
		if (position > size && position < arr.length) {
			arr[++size] = v;
		} else {
			shiftArrRight(position);
			arr[position] = v;
			size++;
		}
	}

	private void shiftArrRight(int position) {
		for (int i = size; i > position; i --) {
			arr[i] = arr[i-1];
		}
	}

	private int findPosition(Key v) {
		if (size == 0 || arr[0].compareTo(v)<0) {
			return size;
		} else if (size < arr.length && v.compareTo(arr[size-1]) < 0) {
			return size;
		} else if (arr[size-1].compareTo(v)>0) {
			return -1;
		} else {
			return binarySearch(v, 0, size);
		}
	}

	private int binarySearch(Key v, int from, int to) {
		if (to == from) {
			return to;
		}
		int middle = (to - from)/2;

		if (v.compareTo(arr[middle]) > 0) {
			return binarySearch(v,middle, to);
		} else {
			return binarySearch(v,from,middle);
		}
	}

	@Override
	public Key pop() {
		Key el = (Key)arr[0];
		shiftArrLeft(0);
		return el;
	}

	private void shiftArrLeft(int from) {
		for (int i = from; i < size - 1; i++) {
			arr[i] = arr[i+1];
		}
		arr[size-1] = null;
		size --;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
