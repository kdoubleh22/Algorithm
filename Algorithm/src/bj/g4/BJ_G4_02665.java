package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -문제
 * 
 * -해설
 * 위치와 count를 넣은 클래스를 만들어 pq 적용.
 */

public class BJ_G4_02665 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output;
	static StringTokenizer tokens;

	static int _n;
	static int[][] _map;
	static int _answer;

	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static boolean isIn(int r, int c) {
		return 0 <= r && r < _n && 0 <= c && c < _n;
	}

	static void bfs() {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		boolean[][] isVisited = new boolean[_n][_n];

		pq.add(new Point(0, 0, 0));
		isVisited[0][0] = true;

		while (!pq.isEmpty()) {
			Point cur = pq.poll();
			int r = cur.r;
			int c = cur.c;
			int cnt = cur.cnt;

//			System.out.println("r:" + r + " c:" + c + " cnt:" + cnt);

			if (r == _n - 1 && c == _n - 1) {
				_answer = cnt;
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];
				if (isIn(nr, nc) && !isVisited[nr][nc]) {
					// 검은 방
					if (_map[nr][nc] == 0) {
						pq.add(new Point(nr, nc, cnt + 1));
						isVisited[nr][nc] = true;
					}
					// 흰 방
					else {
						pq.add(new Point(nr, nc, cnt));
						isVisited[nr][nc] = true;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_map = new int[_n][_n];
		for (int i = 0; i < _n; i++) {
			char[] line = input.readLine().toCharArray();
			for (int j = 0; j < _n; j++) {
				_map[i][j] = line[j] - '0';
			}
		} // 입력 끝.

//		for (int row = 0; row < _n; row++) {
//			System.out.println(Arrays.toString(_map[row]));
//		}

		_answer = -1;
		bfs();

		System.out.println(_answer);

	}

	static class Point implements Comparable<Point> {
		int r, c, cnt; // row col count

		public Point(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.cnt, o.cnt);
		}

	}

}
