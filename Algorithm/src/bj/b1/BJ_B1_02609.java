package bj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B1_02609 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _a, _b;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_a = Integer.parseInt(tokens.nextToken());
		_b = Integer.parseInt(tokens.nextToken());

		int gcd = Integer.MIN_VALUE;
		int smaller = Math.min(_a, _b);

		for (int i = 1; i <= smaller; i++) {
			if (_a % i == 0 && _b % i == 0) {
				gcd = i;
			}
		}

		int lcm = gcd * (_a / gcd) * (_b / gcd);

		output.append(gcd).append("\n").append(lcm);

		System.out.println(output);
	}

}
