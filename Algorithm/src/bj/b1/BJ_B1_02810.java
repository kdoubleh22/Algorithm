package bj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B1_02810 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int n = Integer.parseInt(input.readLine());

		char[] people = input.readLine().toCharArray();

		int can = n + 1;

		int couple = 0;
		for (int i = 0; i < n; i++) {
			if (people[i] == 'L') {
				couple += 1;
			}
		}

		can = can - (couple / 2);
		if (can > n) {
			output.append(n);
		} else {
			output.append(can);
		}

		System.out.println(output);
	}

}
