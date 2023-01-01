package bj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_S4_10828 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		Stack<Integer> s = new Stack<>();
		while (_n-- > 0) {
			tokens = new StringTokenizer(input.readLine());
			String cmd = tokens.nextToken();
			if (cmd.equals("push")) {
				int n = Integer.parseInt(tokens.nextToken());
				s.add(n);
			} else if (cmd.equals("pop")) {
				if (s.isEmpty()) {
					output.append(-1).append("\n");
				} else {
					output.append(s.pop()).append("\n");
				}
			} else if (cmd.equals("size")) {
				output.append(s.size()).append("\n");
			} else if (cmd.equals("empty")) {
				if (s.isEmpty()) {
					output.append(1).append("\n");
				} else {
					output.append(0).append("\n");
				}
			} else { // top
				if (s.isEmpty()) {
					output.append(-1).append("\n");
				} else {
					output.append(s.peek()).append("\n");
				}
			}

		}

		System.out.println(output);

	}

}
