package bj.p4;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_P4_09376 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int _t, _h, _w, _answer;
	static char[][] _map;

	static boolean isIn(int r, int c) {
		return 0 <= r && r < _h && 0 <= c && c < _w;
	}

	static boolean[][] copyDV(boolean[][] doorVisited, int nr, int nc) {
		boolean[][] result = new boolean[_h][];

		for (int i = 0; i < _h; i++) {
			result[i] = Arrays.copyOf(doorVisited[i], _w);
		}

		result[nr][nc] = true;

		return result;
	}

	static void zeroOneBfs(int r, int c, int sdCnt, int svisit) {
		Deque<Point> dq = new ArrayDeque<>();
		boolean[][][] isVisited = new boolean[_h][_w][1 << 2];

		dq.offer(new Point(r, c, sdCnt, svisit, new boolean[_h][_w]));
		isVisited[r][c][svisit] = true;

		while (!dq.isEmpty()) {
			Point cur = dq.poll();

			if (cur.visit == (1 << 2) - 1) {
				_answer = Math.min(_answer, cur.dCnt);
				return;

			}

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];

				if (isIn(nr, nc) && !isVisited[nr][nc][cur.visit] && _map[nr][nc] != '*') {
					// 빈 곳인 경우,
					if (_map[nr][nc] == '.') {
						dq.offerFirst(new Point(nr, nc, cur.dCnt, cur.visit, cur.doorVisited));
						isVisited[nr][nc][cur.visit] = true;
					}
					// 문인 경우,
					else if (_map[nr][nc] == '#') {
						// 처음 방문하는 문인 경우,
						if (!cur.doorVisited[nr][nc]) {
							dq.offerLast(new Point(nr, nc, cur.dCnt + 1, cur.visit, copyDV(cur.doorVisited, nr, nc)));
						}
						// 방문했던 문인 경우,
						else {
							dq.offerFirst(new Point(nr, nc, cur.dCnt, cur.visit, cur.doorVisited));
						}
						isVisited[nr][nc][cur.visit] = true;
					}
					// 죄수인 경우.
					else {
						dq.offerFirst(new Point(nr, nc, cur.dCnt, cur.visit | (1 << _map[nr][nc] - '0' - 1),
								cur.doorVisited));
						isVisited[nr][nc][cur.visit | (1 << _map[nr][nc] - '0' - 1)] = true;
					}
				}
			}

			System.out.println(0);
			for (int i = 0; i < _h; i++) {
				for (int j = 0; j < _w; j++) {
					System.out.print((isVisited[i][j][0] ? 1 : 0) + " ");
				}
				System.out.println();
			}
			System.out.println("===================");

			System.out.println(1);
			for (int i = 0; i < _h; i++) {
				for (int j = 0; j < _w; j++) {
					System.out.print((isVisited[i][j][1] ? 1 : 0) + " ");
				}
				System.out.println();
			}
			System.out.println("===================");

			System.out.println(2);
			for (int i = 0; i < _h; i++) {
				for (int j = 0; j < _w; j++) {
					System.out.print((isVisited[i][j][2] ? 1 : 0) + " ");
				}
				System.out.println();
			}
			System.out.println("===================");

			System.out.println(3);
			for (int i = 0; i < _h; i++) {
				for (int j = 0; j < _w; j++) {
					System.out.print((isVisited[i][j][3] ? 1 : 0) + " ");
				}
				System.out.println();
			}
			System.out.println("===================");
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_t = Integer.parseInt(input.readLine());

		while (_t-- > 0) {
			tokens = new StringTokenizer(input.readLine());
			_h = Integer.parseInt(tokens.nextToken());
			_w = Integer.parseInt(tokens.nextToken());

			_map = new char[_h][];
			char key = '1';
			for (int i = 0; i < _h; i++) {
				_map[i] = input.readLine().toCharArray();
				for (int j = 0; j < _w; j++) {
					if (_map[i][j] == '$') {
						_map[i][j] = key;
						key++;
					}
				}
			}

			for (int i = 0; i < _h; i++) {
				System.out.println(Arrays.toString(_map[i]));
			}

			_answer = Integer.MAX_VALUE;

			// 바깥과 연결된 테두리에서 빈곳이나 문을 찾는다.
			// 위 아래
			for (int c = 0; c < _w; c++) {
				if (_map[0][c] == '.') {
					zeroOneBfs(0, c, 0, 0);
				} else if (_map[0][c] == '#') {
					zeroOneBfs(0, c, 1, 0);
				} else if (_map[0][c] == '1' || _map[0][c] == '2') {
					zeroOneBfs(0, c, 0, _map[0][c] - '0');
				}
				if (_map[_h - 1][c] == '.') {
					zeroOneBfs(_h - 1, c, 0, 0);
				} else if (_map[_h - 1][c] == '#') {
					zeroOneBfs(_h - 1, c, 1, 0);
				} else if (_map[_h - 1][c] == '1' || _map[_h - 1][c] == '2') {
					zeroOneBfs(_h - 1, c, 0, _map[_h - 1][c] - '0');
				}
			}
			// 양 옆
			for (int r = 1; r < _h - 1; r++) {
				if (_map[r][0] == '.') {
					zeroOneBfs(r, 0, 0, 0);
				} else if (_map[r][0] == '#') {
					zeroOneBfs(r, 0, 1, 0);
				} else if (_map[r][0] == '1' || _map[r][0] == '2') {
					zeroOneBfs(r, 0, 0, _map[r][0] - '0');
				}
				if (_map[r][_w - 1] == '.') {
					zeroOneBfs(r, _w - 1, 0, 0);
				} else if (_map[r][_w - 1] == '#') {
					zeroOneBfs(r, _w - 1, 1, 0);
				} else if (_map[r][_w - 1] == '1' || _map[r][_w - 1] == '2') {
					zeroOneBfs(r, _w - 1, 0, _map[r][_w - 1] - '0');
				}
			}

			output.append(_answer).append("\n");
		}

		System.out.println(output);
	}

	static class Point {
		int r, c, dCnt, visit; // door count, prisoner count\
		boolean[][] doorVisited;

		public Point(int r, int c, int dCnt, int visit, boolean[][] doorVisited) {
			super();
			this.r = r;
			this.c = c;
			this.dCnt = dCnt;
			this.visit = visit;
			this.doorVisited = doorVisited;
		}

	}

}
