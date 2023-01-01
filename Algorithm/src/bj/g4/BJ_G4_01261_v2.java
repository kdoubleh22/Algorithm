package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G4_01261_v2 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m, _answer;
	static int[][] _map;

	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_m = Integer.parseInt(tokens.nextToken());
		_n = Integer.parseInt(tokens.nextToken());

		_map = new int[_n][_m];

		for (int i = 0; i < _n; i++) {
			char[] line = input.readLine().toCharArray();
			for (int j = 0; j < _m; j++) {
				_map[i][j] = (int) (line[j] - '0');
			}
		}

		_answer = 0;
		bfs();

		System.out.println(_answer);

	}

	static boolean isIn(int r, int c) {
		return 0 <= r && r < _n && 0 <= c && c < _m;
	}

	private static void bfs() {
		PriorityQueue<Room> pq = new PriorityQueue<>();
		boolean[][] isVisited = new boolean[_n][_m];

		pq.offer(new Room(0, 0, 0));
		isVisited[0][0] = true;

		while (!pq.isEmpty()) {
			Room cur = pq.poll();

			if (cur.r == _n - 1 && cur.c == _m - 1) {
				_answer = cur.bCnt;
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];

				if (isIn(nr, nc) && !isVisited[nr][nc]) {
					// 벽이면 bCnt+1
					if (_map[nr][nc] == 1) {
						pq.offer(new Room(cur.bCnt + 1, nr, nc));
					} else { // 벽이 아니면 bCnt 그대로
						pq.offer(new Room(cur.bCnt, nr, nc));
					}
					isVisited[nr][nc] = true;
				}
			}
		}

	}

	static class Room implements Comparable<Room> {
		int bCnt, r, c;

		public Room(int bCnt, int r, int c) {
			this.bCnt = bCnt;
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Room o) {
			return Integer.compare(this.bCnt, o.bCnt);
		}

	}

}
