package bj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B2_02798 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m;
	static int[] _cards;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());

		_cards = new int[_n];
		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < _n; i++) {
			_cards[i] = Integer.parseInt(tokens.nextToken());
		}

		int answer = Integer.MIN_VALUE;
		int length = _cards.length;
		for (int i = 0; i < length - 2; i++) {
			for (int j = i + 1; j < length - 1; j++) {
				for (int k = j + 1; k < length; k++) {
					int sum = _cards[i] + _cards[j] + _cards[k];
					if (sum <= _m) {
						answer = Math.max(answer, sum);
					}
				}
			}
		}

		System.out.println(answer);

	}

}
