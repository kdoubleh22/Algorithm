package swea.d4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author kdoubleh22
 * @see https://www.acmicpc.net/problem/1260
 * @performance 18156 KB 192 ms
 * @category #그래프 이론 #그래프 탐색 #너비 우선 탐색 #깊이 우선 탐색
 * @memo factorial 전처리. 재귀 종료조건에 return 꼭 쓰기.
 * 
 */

public class SWEA_D4_03234 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _t, _n, _answer, _sum, _halfSum;
	static int[] _weights, _fact;

	static void perm(int cnt, boolean[] isVisited, int left, int right) {
		if (right > left) {
			return;
		}
		if (left >= _halfSum) {
			int diff = _n - cnt;
			_answer += _fact[diff] * Math.pow(2, diff);
			return;
		}
		if (cnt == _n) {
			_answer++;
			return;
		}

		for (int i = 0; i < _n; i++) {
			if (!isVisited[i]) {
				isVisited[i] = true;
				perm(cnt + 1, isVisited, left + _weights[i], right);
				perm(cnt + 1, isVisited, left, right + _weights[i]);
				isVisited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
//		input = new BufferedReader(new FileReader("./io/swea03234input.txt"));

		_t = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= _t; tc++) {
			_n = Integer.parseInt(input.readLine());
			tokens = new StringTokenizer(input.readLine());

			_weights = new int[_n];
			_sum = 0;
			for (int i = 0; i < _n; i++) {
				_weights[i] = Integer.parseInt(tokens.nextToken());
				_sum += _weights[i];
			}

			_fact = new int[9];
			_fact[0] = 1;
			for (int i = 1; i <= 8; i++) {
				_fact[i] = _fact[i - 1] * i;
			}

			_halfSum = (_sum + 1) / 2;
			_answer = 0;
			perm(0, new boolean[_n], 0, 0);

			output.append("#").append(tc).append(" ").append(_answer).append("\n");
		}

		System.out.println(output);
	}

}