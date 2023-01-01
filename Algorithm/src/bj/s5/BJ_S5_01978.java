package bj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S5_01978 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;

	static boolean isPrime(int n) {
		int sqrtN = (int) Math.sqrt(n);
		for (int i = 2; i <= sqrtN; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		int answer = 0;
		tokens = new StringTokenizer(input.readLine());
		while (_n-- > 0) {
			int n = Integer.parseInt(tokens.nextToken());
			if (n == 1) {
				continue;
			}
			if (isPrime(n)) {
				answer++;
			}
		}

		System.out.println(answer);

	}

}
