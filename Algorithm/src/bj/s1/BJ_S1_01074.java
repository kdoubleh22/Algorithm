package bj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S1_01074 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] deltas = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };

	static int _n, _r, _c, _answer;
	static boolean _isFound;

	static boolean isIn(int num, int start, int end) {
		return num >= start && num < end;
	}

	static void divideConquer(int n, int startR, int startC) {
		if (n == 1) {
			for (int i = 0; i < 4; i++) {
				int nr = startR + deltas[i][0];
				int nc = startC + deltas[i][1];
				if (nr == _r && nc == _c) {
					_isFound = true;
					return;
				}
				_answer++;
			}
			return;
		}
		int powed = (int) Math.pow(2, n - 1);
		if (isIn(_r, startR, startR + powed) && isIn(_c, startC, startC + powed)) {
			divideConquer(n - 1, startR, startC);
			if (_isFound) {
				return;
			}
		}
		if (isIn(_r, startR, startR + powed) && isIn(_c, startC + powed, startC + 2 * powed)) {
			_answer += (int) Math.pow(powed, 2);
			divideConquer(n - 1, startR, startC + powed);
			if (_isFound) {
				return;
			}
		}
		if (isIn(_r, startR + powed, startR + 2 * powed) && isIn(_c, startC, startC + powed)) {
			_answer += 2 * (int) Math.pow(powed, 2);
			divideConquer(n - 1, startR + powed, startC);
			if (_isFound) {
				return;
			}
		}
		if (isIn(_r, startR + powed, startR + 2 * powed) && isIn(_c, startC + powed, startC + 2 * powed)) {
			_answer += 3 * (int) Math.pow(powed, 2);
			divideConquer(n - 1, startR + powed, startC + powed);
			if (_isFound) {
				return;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_r = Integer.parseInt(tokens.nextToken());
		_c = Integer.parseInt(tokens.nextToken());

		_answer = 0;
		_isFound = false;
		divideConquer(_n, 0, 0);

		output.append(_answer);
		System.out.print(output);
	}

}
