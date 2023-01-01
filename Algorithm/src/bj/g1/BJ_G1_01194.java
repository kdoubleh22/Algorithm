package bj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G1_01194 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m, _sr, _sc, _answer;
	static char[][] _map;

	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static boolean isIn(int r, int c) {
		return 0 <= r && r < _n && 0 <= c && c < _m;
	}

	// 방문한 적 없으면 false.
	static boolean checkVisit(boolean[][][] isVisited, boolean[] hasKey, int nr, int nc) {
		boolean result = true;
		// 가지고 있는 열쇠로 방문해보지 않은 곳이라면,

		// 키가 없으면,
		if (!hasKey[0]) {
			return isVisited[nr][nc][0];
		} else { // 열쇠가 있으면, 가지고 있는 키로 방문 안해봤으면 false.
			for (int i = 1; i <= 6; i++) {
				if (hasKey[i]) { // 키가 있는데
					if (!isVisited[nr][nc][i]) { // 방문해본적이 없으면
						result = false;
						break;
					}
				}
			}
		}
		return result;
	}

	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		boolean[][][] isVisited = new boolean[_n][_m][7];

		q.offer(new Point(_sr, _sc, new boolean[7]));
		isVisited[_sr][_sc][0] = true;

		while (!q.isEmpty()) {
			int size = q.size();
//			System.out.println("answer:" + _answer);
			while (size-- > 0) {
				Point polled = q.poll();
				boolean[] curHasKey = new boolean[7];
				for (int i = 0; i < 7; i++) {
					curHasKey[i] = polled.hasKey[i];
				}

//				System.out.println("polled.hasKey:" + Arrays.toString(polled.hasKey));

//				System.out.println(polled);

				// 열쇠먹기
				if (_map[polled.r][polled.c] >= 'a' && _map[polled.r][polled.c] <= 'f') {
					curHasKey[_map[polled.r][polled.c] - 'a' + 1] = true;
					curHasKey[0] = true;
				}

				if (_map[polled.r][polled.c] == '1') {
					return;
				}

				for (int d = 0; d < 4; d++) {
					int nr = polled.r + deltas[d][0];
					int nc = polled.c + deltas[d][1];
					if (isIn(nr, nc) && _map[nr][nc] != '#' && !checkVisit(isVisited, curHasKey, nr, nc)) {
						if (_map[nr][nc] >= 'A' && _map[nr][nc] <= 'F') { // 벽이 아니면서, 문인경우
							// 키가 있으면, 갈수있다.
							if (curHasKey[_map[nr][nc] - 'A' + 1]) {
								q.offer(new Point(nr, nc, curHasKey));
								for (int i = 1; i <= 6; i++) {
									isVisited[nr][nc][i] = curHasKey[i];
								}

							}
						} else { // 벽이 아니면서, 문이아닌경우
							q.offer(new Point(nr, nc, curHasKey));
							if (!curHasKey[0]) { // 키가 없으면,
								isVisited[nr][nc][0] = true;
							} else {
								for (int i = 1; i <= 6; i++) {
									isVisited[nr][nc][i] = curHasKey[i];
								}
							}
						}
					}
				}

			}
			_answer++;
		}

		_answer = -1;
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());

		_map = new char[_n][];

		for (int i = 0; i < _n; i++) {
			_map[i] = input.readLine().toCharArray();
			for (int j = 0; j < _m; j++) {
				if (_map[i][j] == '0') {
					_sr = i;
					_sc = j;
				}
			}
		}

		_answer = 0;
		bfs();

		System.out.println(_answer);

	} // main

	static class Point {
		int r, c;
		boolean[] hasKey;

		public Point(int r, int c, boolean[] hasKey) {
			this.r = r;
			this.c = c;
			this.hasKey = hasKey;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", hasKey=" + Arrays.toString(hasKey) + "]";
		}

	}

}
