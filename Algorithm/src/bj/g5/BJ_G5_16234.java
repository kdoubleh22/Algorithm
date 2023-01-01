package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G5_16234 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int _n, _l, _r, _diff, _num, _pops;
	static int[][] _map;
	static boolean[][] _isVisited;

	static boolean isIn(int r, int c) {
		return 0 <= r && r < _n && 0 <= c && c < _n;
	}

	static void dfs(int r, int c) {
		// 방문처리
		_isVisited[r][c] = true;

		// 사방탐색
		for (int d = 0; d < 4; d++) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];
			if (isIn(nr, nc) && !_isVisited[nr][nc] && _map[r][c] - _map[nr][nc] >= _l
					&& _map[r][c] - _map[nr][nc] <= _r) {

			}
		}
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_l = Integer.parseInt(tokens.nextToken());
		_r = Integer.parseInt(tokens.nextToken());

		_map = new int[_n][_n];
		for (int i = 0; i < _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < _n; j++) {
				_map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}

		int answer = 0;
		while (true) {
			boolean isMoved = false;
			_isVisited = new boolean[_n][_n];
			for (int r = 0; r < _n; r++) {
				for (int c = 0; c < _n; c++) {
					if (!_isVisited[r][c]) {
						_pops = 0;
						_num = 0;
						dfs(r, c);
					}
				}
			}

			if (!isMoved) {
				break;
			}
			answer++;
		}

	}

}
