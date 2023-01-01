package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author kdoubleh22
 * @see https://www.acmicpc.net/problem/1987
 * @performance 12464 KB 840 ms
 * @category #그래프 이론 #그래프 탐색 #깊이 우선 탐색 #백트래킹
 * @memo queue는 array보다 list로 구현하는게 낫다. 앞에서 poll이 이루어지기 때문에, array로 하면 비용이 많이
 *       든다.
 * 
 */

public class BJ_G4_01987 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상우하좌

	static int _r, _c, _answer;
	static char[][] _alphabets;
	static int _distance[][];
	static boolean[] _isVisited;

	static boolean isIn(int r, int c) {
		return r >= 0 && r < _r && c >= 0 && c < _c;
	}

	static void dfs(int r, int c, int distance) {
		distance++;
		if (distance > _answer) {
			_answer = distance;
		}
		_isVisited[_alphabets[r][c] - 'A'] = true;
		for (int i = 0; i < 4; i++) {
			int nr = r + deltas[i][0];
			int nc = c + deltas[i][1];
			if (isIn(nr, nc) && !_isVisited[_alphabets[nr][nc] - 'A']) {
				dfs(nr, nc, distance);
			}
		}
		_isVisited[_alphabets[r][c] - 'A'] = false;
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_r = Integer.parseInt(tokens.nextToken());
		_c = Integer.parseInt(tokens.nextToken());

		_alphabets = new char[_r][_c];
		for (int i = 0; i < _r; i++) {
			_alphabets[i] = input.readLine().toCharArray();
		} // 입력 끝.

		_distance = new int[_r][_c];
		_isVisited = new boolean[26];

		_answer = Integer.MIN_VALUE;
		dfs(0, 0, 0);

		output.append(_answer);
		System.out.print(output);
	}

}
