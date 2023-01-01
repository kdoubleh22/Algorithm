package bj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B2_02231 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		boolean isFinded = false;
		for (int i = 1; i < _n; i++) {
			int sum = i;
			int n = i;
			while (n != 0) {
				sum += n % 10;
				n /= 10;
			}
			if (sum == _n) {
				output.append(i);
				isFinded = true;
				break;
			}
		}

		if (!isFinded) {
			output.append(0);
		}

		System.out.println(output);

	}

}
