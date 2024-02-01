package edu.algo.misc;

import java.util.Stack;

import javax.annotation.processing.SupportedSourceVersion;

/**
 * Dijkstra's algorithm:
 *  going from left to right:
 *   - put values into valStack
 *   - put operators into appStack
 *   - once right parenthesis - pop 2 values and operators and evaluate exp.
 *
 */
public class ArithmeticExpressionEvaluation {
	public static void main(String[] args) {
		String input = "((3 * (2 + 3)) * (5-3))";

		Stack<Character> ops = new Stack<>();
		Stack<Integer> vals = new Stack<>();

		for(char c : input.toCharArray()) {
			if (c == '(');
			else if (c == '+'|| c=='*' || c=='-') {
				ops.push(c);
			} else if (c==')') {
				Integer v1 = vals.pop();
				Integer v2 = vals.pop();
				Character op = ops.pop();

				if (op.equals('+')) vals.add(v1 + v2);
				if (op.equals('*')) vals.add(v1 * v2);
				if (op.equals('-')) vals.add(v2 - v1);

			} else if (Character.isDigit(c)) {
				vals.push(Character.getNumericValue(c));
			}
		}

		System.out.println("result is " + vals.pop());
	}

}
