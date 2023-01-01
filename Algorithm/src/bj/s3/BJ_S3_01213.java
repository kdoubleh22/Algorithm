package bj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_S3_01213 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static char[] _name;
	static int[] _alphabets;

	public static void main(String[] args) throws IOException {
		_name = input.readLine().toCharArray();
		_alphabets = new int[26];

		for (int i = 0; i < _name.length; i++) {
			_alphabets[_name[i] - 'A'] += 1;
		}

		int odd_count = 0;
		char odd_alphabet = 'a';

		for (int i = 0; i < _alphabets.length; i++) {
			if (_alphabets[i] % 2 == 1) {
				odd_count += 1;
				odd_alphabet = (char) (i + 'A');
			}
		}

		Stack<Character> s = new Stack<>();

		if (odd_count > 1) {
			output.append("I'm Sorry Hansoo");
		} else {
			for (int i = 0; i < _alphabets.length; i++) {
				for (int j = 0; j < _alphabets[i] / 2; j++) {
					output.append((char) (i + 'A'));
					s.add((char) (i + 'A'));
				}
			}

			if (odd_count == 1) {
				output.append(odd_alphabet);
			}

			while (!s.isEmpty()) {
				output.append(s.pop());
			}

		}

		System.out.println(output);

	}

}
