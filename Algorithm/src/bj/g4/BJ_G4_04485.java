package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G4_04485 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _answer;
	static int[][] _cave;
	static int[][] _distance;

	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static boolean isIn(int r, int c) {
		return r >= 0 && r < _n && c >= 0 && c < _n;
	}

	static void dijkstra() {
		// 준비물
		PriorityQueue<Point> pq = new PriorityQueue<>();
		int[][] aws = new int[_n][_n];

		for (int i = 0; i < _n; i++) {
			Arrays.fill(aws[i], Integer.MAX_VALUE); // 무한대 상태로 초기화.
		}

		aws[0][0] = 0; // 시작함
		pq.offer(new Point(0, 0, _cave[0][0]));

		while (!pq.isEmpty()) {
			Point head = pq.poll();

			if (head.r == _n - 1 && head.c == _n - 1) {
				_answer = head.aw;
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nr = head.r + deltas[d][0];
				int nc = head.c + deltas[d][1];
				if (isIn(nr, nc) && aws[nr][nc] > head.aw + _cave[nr][nc]) {
					aws[nr][nc] = head.aw + _cave[nr][nc]; // 거리 업데이트
					pq.offer(new Point(nr, nc, aws[nr][nc]));
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		int tc = 1;
		while (true) {
			_n = Integer.parseInt(input.readLine());

			if (_n == 0) {
				break;
			}

			_cave = new int[_n][_n];
			for (int i = 0; i < _n; i++) {
				tokens = new StringTokenizer(input.readLine());
				for (int j = 0; j < _n; j++) {
					_cave[i][j] = Integer.parseInt(tokens.nextToken());
				}
			} // 입력

			_answer = -1;
			dijkstra();

			output.append("Problem ").append(tc).append(": ").append(_answer).append("\n");

			tc++;
		}

		System.out.println(output);

	}

	static class Point implements Comparable<Point> {
		int r, c, aw;

		public Point(int r, int c, int aw) {
			this.r = r;
			this.c = c;
			this.aw = aw;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.aw, o.aw);
		}

	}

}
