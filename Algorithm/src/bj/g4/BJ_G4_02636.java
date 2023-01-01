package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G4_02636 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _r, _c;
	static int[][] _map;
	static List<Point> _cheese;

	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static boolean isIn(int r, int c) {
		return 0 <= r && r < _r && 0 <= c && c < _c;
	}

	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] isVisited = new boolean[_r][_c];

		_map[0][0] -= 1;
		int air = _map[0][0];
		q.offer(new Point(0, 0));
		isVisited[0][0] = true;

		while (!q.isEmpty()) {
			Point polled = q.poll();

			int r = polled.r;
			int c = polled.c;
			_map[r][c] = air;

			for (int d = 0; d < 4; d++) {
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];
				if (isIn(nr, nc) && _map[nr][nc] != 1 && !isVisited[nr][nc]) { // 치즈가 아니면,
					q.offer(new Point(nr, nc));
					isVisited[nr][nc] = true;
				}
			}
		}
	}

	static void removeCheese() {
		for (int i = _cheese.size() - 1; i >= 0; i--) {
			Point p = _cheese.get(i);
			int r = p.r;
			int c = p.c;
			for (int d = 0; d < 4; d++) {
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];
				if (isIn(nr, nc) && _map[nr][nc] < 0) {
					_cheese.remove(i);
					_map[r][c] = 0;
					break;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_r = Integer.parseInt(tokens.nextToken());
		_c = Integer.parseInt(tokens.nextToken());

		_map = new int[_r][_c];
		_cheese = new LinkedList<>();
		for (int i = 0; i < _r; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < _c; j++) {
				_map[i][j] = Integer.parseInt(tokens.nextToken());
				if (_map[i][j] == 1) {
					_cheese.add(new Point(i, j));
				}
			}
		}

		int answer = -1;
		int time = 0;
		while (_cheese.size() > 0) {
			answer = _cheese.size();
			// 공기는 음수로 업데이트
			bfs();
			// 치즈 삭제.
			removeCheese();
			time++;
		}
		System.out.println(time);
		System.out.println(answer);

	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

}
