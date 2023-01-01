package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G5_10026 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static int _n;
	static char[][] _map;

	static boolean isIn(int r, int c) {
		return r >= 0 && r < _n && c >= 0 && c < _n;
	}

	static void dfs(int r, int c, boolean[][] isVisited, char color) {
		isVisited[r][c] = true;

		for (int i = 0; i < 4; i++) {
			int nr = r + deltas[i][0];
			int nc = c + deltas[i][1];
			if (isIn(nr, nc) && !isVisited[nr][nc] && _map[nr][nc] == color) {
				dfs(nr, nc, isVisited, color);
			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_map = new char[_n][];
		for (int i = 0; i < _n; i++) {
			_map[i] = input.readLine().toCharArray();
		}

		boolean[][] isVisited1 = new boolean[_n][_n];
		int result1 = 0;
		for (int i = 0; i < _n; i++) {
			for (int j = 0; j < _n; j++) {
				if (!isVisited1[i][j]) {
					dfs(i, j, isVisited1, _map[i][j]);
					result1++;
				}
			}
		}

		for (int i = 0; i < _n; i++) {
			for (int j = 0; j < _n; j++) {
				if (_map[i][j] == 'R') {
					_map[i][j] = 'G';
				}
			}
		}

		boolean[][] isVisited2 = new boolean[_n][_n];
		int result2 = 0;
		for (int i = 0; i < _n; i++) {
			for (int j = 0; j < _n; j++) {
				if (!isVisited2[i][j]) {
					dfs(i, j, isVisited2, _map[i][j]);
					result2++;
				}
			}
		}

		output.append(result1).append(" ").append(result2);

		System.out.println(output);

	}

}
