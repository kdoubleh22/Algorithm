package bj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S5_02751 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static boolean[] _b;

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_b = new boolean[2000001];

		for (int i = 0; i < _n; i++) {
			_b[Integer.parseInt(input.readLine()) + 1000000] = true;
		}

		for (int i = 0; i < 2000001; i++) {
			if (_b[i])
				output.append(i - 1000000).append("\n");
		}

		System.out.println(output);

	}

}
