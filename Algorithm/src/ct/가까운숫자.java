package ct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class 가까운숫자 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		tokens = new StringTokenizer(input.readLine());

		TreeSet<Integer> ts = new TreeSet<>();
		ts.add(0);
		int min = Integer.MAX_VALUE;
		while (_n-- > 0) {
			int num = Integer.parseInt(tokens.nextToken());
			ts.add(num);
			Integer lower = ts.lower(num);
			Integer higher = ts.higher(num);
			if (lower != null) {
				if (Math.abs(num - lower) < min) {
					min = Math.abs(num - lower);
				}
			}
			if (higher != null) {
				if (Math.abs(num - higher) < min) {
					min = Math.abs(num - higher);
				}
			}
			output.append(min).append("\n");
		}

		System.out.println(output);

	}

}
