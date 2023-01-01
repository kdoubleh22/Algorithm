package bj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_S1_01149 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static int[][] _memo, _costs;

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_memo = new int[3][_n + 1]; // n번째 집에서 각 색깔을 골랐을 때의 최소비용
		_costs = new int[3][_n + 1];

		tokens = new StringTokenizer(input.readLine());
		_memo[0][1] = Integer.parseInt(tokens.nextToken()); // Red
		_memo[1][1] = Integer.parseInt(tokens.nextToken()); // Green
		_memo[2][1] = Integer.parseInt(tokens.nextToken()); // Blue

		for (int i = 2; i <= _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			int R = Integer.parseInt(tokens.nextToken());
			int G = Integer.parseInt(tokens.nextToken());
			int B = Integer.parseInt(tokens.nextToken());

			_memo[0][i] = R + Math.min(_memo[1][i - 1], _memo[2][i - 1]);
			_memo[1][i] = G + Math.min(_memo[0][i - 1], _memo[2][i - 1]);
			_memo[2][i] = B + Math.min(_memo[0][i - 1], _memo[1][i - 1]);
		}

		int answer = Math.min(_memo[0][_n], _memo[1][_n]);
		answer = Math.min(answer, _memo[2][_n]);
		System.out.println(answer);

	}
}