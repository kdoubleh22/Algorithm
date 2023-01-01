package bj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S4_11047 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n; // 동전의 종류
	static int _k; // 가치의 합
	static int[] _prices; // 동전의 가치

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());

		_n = Integer.parseInt(tokens.nextToken());
		_k = Integer.parseInt(tokens.nextToken());

		_prices = new int[_n];

		for (int i = 0; i < _n; i++) {
			_prices[i] = Integer.parseInt(input.readLine());
		}

		int answer = 0;
		for (int i = _n - 1; i > -1; i--) {
			if (_k >= _prices[i]) {
				answer += _k / _prices[i];
				_k = _k % _prices[i];
			}
		}

		System.out.println(answer);

	}

}
