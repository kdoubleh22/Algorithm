package swea.nd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA_ND_05656 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _t, _n, _w, _h, _ab, _answer, _ac; // _ab:all brick 개수, _ac:answer candidate
	static int[][] _map, _mapCopy;
	static int[] _pr; // permutation result

	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static boolean isIn(int r, int c) {
		return 0 <= r && r < _h && 0 <= c && c < _w;
	}

	static void bfs(int sr, int sc) {
		Queue<Coordinate> q = new LinkedList<>();
		boolean[][] isVisited = new boolean[_h][_w];
		Set<Coordinate> s = new HashSet<>();

		q.offer(new Coordinate(sr, sc));
		s.add(new Coordinate(sr, sc));
		isVisited[sr][sc] = true;

		while (!q.isEmpty()) {
			Coordinate polled = q.poll();
			int r = polled.r;
			int c = polled.c;

			for (int d = 0; d < 4; d++) {
				for (int idx = 0; idx < _mapCopy[r][c]; idx++) {
					int nr = r + idx * deltas[d][0];
					int nc = c + idx * deltas[d][1];
					if (isIn(nr, nc) && !isVisited[nr][nc] && _mapCopy[nr][nc] != 0) {
						q.offer(new Coordinate(nr, nc));
						s.add(new Coordinate(nr, nc));
						isVisited[nr][nc] = true;
					}
				}
			}
		}

		// 삭제될만큼 빼주고,
		_ac -= s.size();
		// 삭제
		for (Coordinate c : s) {
			_mapCopy[c.r][c.c] = 0;
		}

	}

	static void shoot(int bp) {
		// find top
		int top = -1;
		for (int h = 0; h < _h; h++) {
			if (_mapCopy[h][bp] != 0) {
				top = h;
				break;
			}
		}
		if (top != -1) {
			bfs(top, bp);
		}
	}

	static void down() {
		for (int c = 0; c < _w; c++) {
			Queue<Integer> q = new LinkedList<>();
			for (int r = _h - 1; r >= 0; r--) {
				if (_mapCopy[r][c] != 0) {
					q.offer(_mapCopy[r][c]);
					_mapCopy[r][c] = 0;
				}
			}
			int idx = _h - 1;
			while (!q.isEmpty()) {
				_mapCopy[idx][c] = q.poll();
				idx--;
			}
		}
	}

	static void bb() { // break bricks
		// map copy
		_mapCopy = new int[_h][];
		for (int i = 0; i < _h; i++) {
			_mapCopy[i] = Arrays.copyOf(_map[i], _w);
		}

		for (int bp : _pr) { // break point
			shoot(bp);
			down();
		}
	}

	static void dp(int cnt) { // duplicate permutation
		if (cnt == _n) {
			_ac = _ab;
			bb();
			_answer = Math.min(_answer, _ac);
			return;
		}

		for (int i = 0; i < _w; i++) {
			_pr[cnt] = i;
			dp(cnt + 1);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_t = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= _t; tc++) {
			tokens = new StringTokenizer(input.readLine());
			_n = Integer.parseInt(tokens.nextToken());
			_w = Integer.parseInt(tokens.nextToken());
			_h = Integer.parseInt(tokens.nextToken());

			_map = new int[_h][_w];
			_ab = 0;
			for (int i = 0; i < _h; i++) {
				tokens = new StringTokenizer(input.readLine());
				for (int j = 0; j < _w; j++) {
					_map[i][j] = Integer.parseInt(tokens.nextToken());
					if (_map[i][j] != 0) {
						_ab++;
					}
				}
			}

			_answer = Integer.MAX_VALUE;
			_pr = new int[_n];
			dp(0);

			output.append("#").append(tc).append(" ").append(_answer).append("\n");

		} // tc

		System.out.println(output);

	} // main

	static class Coordinate {
		int r, c;

		public Coordinate(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

}
