package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G5_12865_v2 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _k;
	static int[][] _memo;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_k = Integer.parseInt(tokens.nextToken());

		_memo = new int[_n + 1][_k + 1];
		Arrays.fill(_memo[0], 0); // 물품이 0개이면 가치는 0.
		for (int i = 1; i <= _n; i++) { // 무게가 0일때는 가치가 0.
			_memo[i][0] = 0;
		}

		for (int n = 1; n <= _n; n++) {
			tokens = new StringTokenizer(input.readLine());
			int w = Integer.parseInt(tokens.nextToken());
			int v = Integer.parseInt(tokens.nextToken());
			for (int k = 1; k <= _k; k++) {
				if (w > k) { // 물건의 무게가 현재 고려하는 최대 무게보다 크면,
					_memo[n][k] = _memo[n - 1][k]; // 넣지 못하니까 이전무게를 고려한 최대값.
					continue;
				}
				// 현재 물건을 포함하거나, 포함하지않거나 둘 중에 큰 값.
				_memo[n][k] = Math.max(_memo[n - 1][k], _memo[n - 1][k - w] + v);

			}
		}

		System.out.println(_memo[_n][_k]);

	}

}
