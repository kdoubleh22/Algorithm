package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * -문제
 * 싸이클 찾기.
 * -풀이
 * dfs를 돌면서 depth가 3이상 차이나고, 방문했던 곳이 있으면 싸이클이다.
 */

public class BJ_G4_16929 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m;
	static char[][] _map;
	static boolean[][] _outVisited;
	static boolean _yes;

	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static boolean isIn(int r, int c) {
		return 0 <= r && r < _n && 0 <= c && c < _m;
	}

	static boolean dfs(char a, int r, int c, int depth, int[][] depths, boolean[][] visited) {
//		System.out.println("r:" + r + " c:" + c + " a:" + a + " depth:" + depth);
		boolean result = false;
		// 방문처리
		visited[r][c] = true;
		// depth 저장
		depths[r][c] = depth;

		for (int d = 0; d < 4; d++) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];

			if (isIn(nr, nc) && _map[nr][nc] == a) {
				// 방분해본 적이 있으면, 현재 depth가 4이상이면 싸이클이다.
				if (visited[nr][nc]) {
					if (depth - depths[nr][nc] >= 3) {
						return true;
					}
				}
				// 없으면, 방문대상이다.
				else {
					if (dfs(a, nr, nc, depth + 1, depths, visited)) {
						return true;
					}
				}
			}
		}

		return result;
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());

		_map = new char[_n][];

		for (int i = 0; i < _n; i++) {
			_map[i] = input.readLine().toCharArray();
		} // 입력 끝.

//		for (int r = 0; r < _n; r++) {
//			System.out.println(Arrays.toString(_map[r]));
//		}

		_outVisited = new boolean[_n][_m];
		_yes = false;
		outer: for (int r = 0; r < _n; r++) {
			for (int c = 0; c < _m; c++) {
				if (!_outVisited[r][c]) {
					if (dfs(_map[r][c], r, c, 1, new int[_n][_m], _outVisited)) {
						_yes = true;
						break outer;
					}
				}
			}
		}

		if (_yes) {
			output.append("Yes");
		} else {
			output.append("No");
		}

		System.out.println(output);
	}

}
