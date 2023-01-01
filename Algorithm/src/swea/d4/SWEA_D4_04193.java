package swea.d4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_D4_04193 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int _t, _n, _a, _b, _c, _d, _answer;
	static int[][] _map;

	static boolean isIn(int r, int c) {
		return 0 <= r && r < _n && 0 <= c && c < _n;
	}

	static void bfs() {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		boolean[][] isVisited = new boolean[_n][_n];

		pq.add(new Point(_a, _b, 0));

		while (!pq.isEmpty()) {
			Point cur = pq.poll();

//			System.out.println("cur:" + cur);

			int r = cur.r;
			int c = cur.c;
			int t = cur.t;

			if (r == _c && c == _d) {
				_answer = t;
				return;
			}

			isVisited[r][c] = true;

			for (int d = 0; d < 4; d++) {
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];

				if (isIn(nr, nc) && _map[nr][nc] != 1 && !isVisited[nr][nc]) {
					if (_map[nr][nc] == 0) {
						pq.add(new Point(nr, nc, t + 1));
//						System.out.println("add" + nr + " : " + nc + " : " + (t + 1));
					} else { // _map[nr][nc]==2
						if (t % 3 == 1) {
							pq.add(new Point(nr, nc, t + 2));
//							System.out.println("add" + nr + " : " + nc + " : " + (t + 1));
						} else if (t % 3 == 2) {
							pq.add(new Point(nr, nc, t + 1));
//							System.out.println("add" + nr + " : " + nc + " : " + (t + 3));
						} else { // t%3==0
							pq.add(new Point(nr, nc, t + 3));
//							System.out.println("add" + nr + " : " + nc + " : " + (t + 2));
						}
					}
				}
			}

		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
//		input = new BufferedReader(new FileReader("./io/swea04193input.txt"));
		_t = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= _t; tc++) {
			_n = Integer.parseInt(input.readLine());

			_map = new int[_n][_n];

			for (int i = 0; i < _n; i++) {
				tokens = new StringTokenizer(input.readLine());
				for (int j = 0; j < _n; j++) {
					_map[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}

//			for (int[] row : _map) {
//				System.out.println(Arrays.toString(row));
//			}

			tokens = new StringTokenizer(input.readLine());
			_a = Integer.parseInt(tokens.nextToken());
			_b = Integer.parseInt(tokens.nextToken());
			tokens = new StringTokenizer(input.readLine());
			_c = Integer.parseInt(tokens.nextToken());
			_d = Integer.parseInt(tokens.nextToken());

			_answer = -1;
			bfs();

			output.append("#").append(tc).append(" ").append(_answer).append("\n");
		}

		System.out.println(output);

	}

	static class Point implements Comparable<Point> {
		int r, c, t;

		public Point(int r, int c, int t) {
			super();
			this.r = r;
			this.c = c;
			this.t = t;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", t=" + t + "]";
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.t, o.t);
		}

	}

}
