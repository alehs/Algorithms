package edu.algo.sort;
import java.util.Random;

enum PivotChooser {

    FIRST {
		@Override
		public int choose(int[] arr, int left, int right) {
			return left;
		}
	},

	LAST {
		@Override
		public int choose(int[] arr, int left, int right) {
			return right;
		}
	},

	MEDIAN {
		@Override
		public int choose(int[] arr, int left, int right) {
			int size = (right - left);

			if (size < 2) {
				return left;
			}

			int middle = left + size / 2;

			int fst = arr[left];
			int sec = arr[middle];
			int frd = arr[right];

			if (fst > sec && fst > frd) {
				if (sec > frd) {
					return middle;
				} else {
					return right;
				}
			} else if (fst < sec && fst < frd) {
				if (sec < frd) {
					return middle;
				} else {
					return right;
				}
			} else {
				return left;
			}

		}
	},

	RANDOM {
		Random r = new Random();
		@Override
		public int choose(int[] arr, int left, int right) {
			int size = right - left;
			return left + r.nextInt(size);
		}

	};

	public abstract int choose(int[] arr, int left, int right);
}
