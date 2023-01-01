package bj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S3_01929 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _m, _n;

	static boolean isPrime(int n) {
		if (n == 1) {
			return false;
		}
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_m = Integer.parseInt(tokens.nextToken());
		_n = Integer.parseInt(tokens.nextToken());

		for (int i = _m; i <= _n; i++) {
			if (isPrime(i)) {
				output.append(i).append("\n");
			}
		}

		System.out.println(output);

	}

}
