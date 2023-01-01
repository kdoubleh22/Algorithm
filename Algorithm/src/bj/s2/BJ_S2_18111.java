package bj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S2_18111 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m, _b, _min, _max;
	static int[][] _heights;

	static int make(int height) {
		int b = _b;
		int answer = 0;
		for (int i = 0; i < _n; i++) {
			for (int j = 0; j < _m; j++) {
				if (_heights[i][j] > height) {
					answer += (_heights[i][j] - height) * 2;
					b++;
				} else if (_heights[i][j] < height) {
					answer += height - _heights[i][j];
					b--;
				}
			}
		}
		if (b >= 0) {
			return answer;
		} else {
			return Integer.MAX_VALUE;
		}
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());
		_b = Integer.parseInt(tokens.nextToken());

		_heights = new int[_n][_m];
		_min = Integer.MAX_VALUE;
		_max = Integer.MIN_VALUE;
		for (int i = 0; i < _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < _m; j++) {
				_heights[i][j] = Integer.parseInt(tokens.nextToken());
				_max = Math.max(_max, _heights[i][j]);
				_min = Math.min(_min, _heights[i][j]);
			}
		} // 입력 끝.

		int answer = Integer.MAX_VALUE;
		int answerHeight = -1;
		for (int i = _min; i <= _max; i++) {
			int result = make(i);
			System.out.println("i:" + i + " result:" + result);
			if (result <= answer) {
				answer = result;
				answerHeight = i;
			}
		}
		output.append(answer).append(" ").append(answerHeight);
		System.out.println(output);

	}

}
