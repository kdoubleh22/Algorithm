package bj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S1_01189 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output;
	static StringTokenizer tokens;;

	static int _r, _c, _k, _answer;
	static char[][] _map;

	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static boolean isIn(int r, int c) {
		return 0 <= r && r < _r && 0 <= c && c < _c;
	}

	static void dfs(int r, int c, boolean[][] isVisited, int dist) {
		// 방문처리.
		isVisited[r][c] = true;
		dist++;

		// 종료조건
		if (dist == _k) {
			if (r == 0 && c == _c - 1) {
				_answer += 1;
			}
			isVisited[r][c] = false;
			return;
		}

		// 사방탐색
		for (int d = 0; d < 4; d++) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];
			if (isIn(nr, nc) && _map[nr][nc] != 'T' && !isVisited[nr][nc]) {
				dfs(nr, nc, isVisited, dist);
			}
		}

		isVisited[r][c] = false;

	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_r = Integer.parseInt(tokens.nextToken());
		_c = Integer.parseInt(tokens.nextToken());
		_k = Integer.parseInt(tokens.nextToken());

		_map = new char[_r][];
		for (int i = 0; i < _r; i++) {
			_map[i] = input.readLine().toCharArray();
		}

		_answer = 0;
		dfs(_r - 1, 0, new boolean[_r][_c], 0);

		System.out.println(_answer);
	}

}
