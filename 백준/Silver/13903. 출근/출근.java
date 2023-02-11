import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _r, _c;
	static int[][] _map;
	static int _n;
	static int _deltas[][];
	static int _answer;

	static boolean isIn(int r, int c) {
		return 0 <= r && r < _r && 0 <= c && c < _c;
	}

	static void bfs() {
		boolean[][] visited = new boolean[_r][_c];
		Queue<Point> q = new LinkedList<>();

		for (int c = 0; c < _c; c++) {
			if (_map[0][c] == 1) {
				q.add(new Point(0, c, 0));
				visited[0][c] = true;
			}
		}

		while (!q.isEmpty()) {
			Point cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int cnt = cur.cnt;

			if (r == _r - 1) {
				_answer = Math.min(_answer, cnt);
				return;
			}

			for (int d = 0; d < _deltas.length; d++) {
				int nr = r + _deltas[d][0];
				int nc = c + _deltas[d][1];
				if (isIn(nr, nc) && _map[nr][nc] == 1 && !visited[nr][nc]) {
					q.add(new Point(nr, nc, cnt + 1));
					visited[nr][nc] = true;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		_r = Integer.parseInt(tokens.nextToken());
		_c = Integer.parseInt(tokens.nextToken());

		_map = new int[_r][_c];
		for (int r = 0; r < _r; r++) {
			tokens = new StringTokenizer(input.readLine());
			for (int c = 0; c < _c; c++) {
				_map[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}

		_n = Integer.parseInt(input.readLine());
		_deltas = new int[_n][2];
		for (int i = 0; i < _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			_deltas[i][0] = Integer.parseInt(tokens.nextToken());
			_deltas[i][1] = Integer.parseInt(tokens.nextToken());
		}

		_answer = Integer.MAX_VALUE;
		bfs();

		if (_answer == Integer.MAX_VALUE) {
			output.append(-1);
		} else {
			output.append(_answer);
		}

		System.out.println(output);
	}

	static class Point {
		int r;
		int c;
		int cnt;

		public Point(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

	}

}