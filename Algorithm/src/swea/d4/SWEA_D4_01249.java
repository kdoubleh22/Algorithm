package swea.d4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_D4_01249 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _t, _n, _answer;
	static int[][] _map;

	static int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	static boolean isIn(int r, int c) {
		return 0 <= r && r < _n && 0 <= c && c < _n;
	}

	static void dijkstra() {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		int[][] md = new int[_n][_n]; // minimum distance
		for (int[] row : md) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}

		md[0][0] = 0;
		pq.offer(new Point(0, 0, 0));

		while (!pq.isEmpty()) {
			Point polled = pq.poll();

			if (polled.ac > md[polled.r][polled.c]) {
				continue;
			}

			if (polled.r == _n - 1 && polled.c == _n - 1) {
				_answer = polled.ac;
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nr = polled.r + deltas[d][0];
				int nc = polled.c + deltas[d][1];

				if (isIn(nr, nc) && (polled.ac + _map[nr][nc] < md[nr][nc])) {
					md[nr][nc] = polled.ac + _map[nr][nc];
					pq.offer(new Point(nr, nc, md[nr][nc]));
				}
			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
//		input = new BufferedReader(new FileReader("./io/swea01249input.txt"));// d

		_t = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= _t; tc++) {
			_n = Integer.parseInt(input.readLine());
			_map = new int[_n][_n];

			for (int i = 0; i < _n; i++) {
				char[] charArr = input.readLine().toCharArray();
				for (int j = 0; j < _n; j++) {
					_map[i][j] = charArr[j] - '0';
				}
			} // 입력 끝.

//			for (int[] row : _map) {
//				System.out.println(Arrays.toString(row));
//			}

			_answer = -1;
			dijkstra();

			output.append("#").append(tc).append(" ").append(_answer).append("\n");

		}

		System.out.println(output);

	}

	static class Point implements Comparable<Point> {
		int r, c, ac; // accumulated cost

		public Point(int r, int c, int ac) {
			this.r = r;
			this.c = c;
			this.ac = ac;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.ac, o.ac);
		}

	}

}
