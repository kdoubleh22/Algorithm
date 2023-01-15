package bj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * -풀이
 * impossible을 -1로 해야 0일 때도 처리할 수 있음.
 */

public class BJ_S2_17245_v2 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static int[] _heights;
	static long _total;

	public static void main(String[] args) throws IOException {
		_n = Integer.parseInt(input.readLine());

		_heights = new int[_n * _n]; // 각 칸에 몇 대씩 쌓여있는지.
		_total = 0; // 컴퓨터 전체 개수.
		int idx = 0;
		for (int i = 0; i < _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < _n; j++) {
				int computers = Integer.parseInt(tokens.nextToken());
				_heights[idx++] = computers;
				_total += computers;
			}
		}

		long half = (_total + 1) / 2;

		int impossible = -1;
		int possible = 10000000;
		int length = _heights.length;
		while (impossible + 1 != possible) {

			int cur = (impossible + possible + 1) / 2;
//			System.out.println("impossible:" + impossible + " possible:" + possible + " cur:" + cur);

			long on = 0;
			for (int i = 0; i < length; i++) {
				if (cur >= _heights[i]) {
					on += _heights[i];
				} else { // cur < _heights[i]
					on += cur;
				}
			}

			if (on >= half) {
				possible = cur;
			} else if (on < half) {
				impossible = cur;
			}

		}

		System.out.println(possible);
	}
}
