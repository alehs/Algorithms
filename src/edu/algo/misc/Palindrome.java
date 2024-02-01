package edu.algo.misc;

/**
 * Remove a Character From a String to Make it a Palindrome
 */
public class Palindrome {

	public static void main(String[] args) {
		String input = "Sir, I demand, I am a maid named Iris";
		System.out.println(input + " is palindrome: " + isPalindrome(input.toCharArray()));
	}

	private static boolean isPalindrome(char[] input) {
		int start = 0, end = input.length - 1;
		return isPalindrome(input, start, end, true);
	}

	private static boolean isPalindrome(char[] input, int start, int end, boolean skipChar) {
		boolean isPalindrome = true;
		while (start < end) {

			// not a char
			while(Character.toLowerCase(input[start]) == Character.toUpperCase(input[start])) {
				start++;
			}

			while(Character.toLowerCase(input[end]) == Character.toUpperCase(input[end])) {
				end--;
			}

			if (Character.toLowerCase(input[start]) == Character.toLowerCase(input[end])) {
				start ++;
				end --;
			} else if (skipChar) {
				isPalindrome = isPalindrome(input, start+1, end, false)
						|| isPalindrome(input, start, end-1, false);
				break;
			} else {
				isPalindrome = false;
				break;
			}
		}
		return isPalindrome;
	}

}
