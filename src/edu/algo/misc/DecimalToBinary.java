package edu.algo.misc;

import java.util.Stack;

public class DecimalToBinary {
	public static void main(String[] args) {
		int n = 11;
		Stack<Integer> stack = new Stack<>();
		while (n>0) {
			stack.push(n%2);
			n = n/2;
		}

		while (!stack.empty()) {
			System.out.print(stack.pop());
		}
	}
}
