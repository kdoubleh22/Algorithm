package bj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_S4_09012 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _t;

	static void isVPS() throws IOException {
		char[] parens = input.readLine().toCharArray();
		Stack<Character> s = new Stack<>();

		for (int i = 0; i < parens.length; i++) {
			char p = parens[i];
			if (p == '(') {
				s.add(p);
			} else { // p==')'
				if (s.isEmpty()) {
					output.append("NO\n");
					return;
				} else {
					s.pop();
				}
			}
		}

		if (s.isEmpty()) {
			output.append("YES\n");
		} else {
			output.append("NO\n");
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_t = Integer.parseInt(input.readLine());

		while (_t-- > 0) {
			isVPS();
		}

		System.out.println(output);
	}

}
