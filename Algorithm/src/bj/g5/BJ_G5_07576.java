package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G5_07576 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static int _n, _m, _notRipedNum;
	static Queue<Point> _riped;
	static int[][] _map;
	static boolean[][] _isVisited;

	static boolean isIn(int r, int c) {
		return r >= 0 && r < _n && c >= 0 && c < _m;
	}

	static int bfs() {
		int day = 0;
		while (!_riped.isEmpty()) {
			int size = _riped.size();
			for (int i = 0; i < size; i++) {
				Point polled = _riped.poll();
				int r = polled.r;
				int c = polled.c;

				if (--_notRipedNum == 0) {
					return day;
				}

				for (int j = 0; j < 4; j++) {
					int nr = r + deltas[j][0];
					int nc = c + deltas[j][1];
					if (isIn(nr, nc) && !_isVisited[nr][nc] && _map[nr][nc] == 0) {
						_map[nr][nc] = 1;
						_riped.offer(new Point(nr, nc));
						_isVisited[nr][nc] = true;
					}
				}
			}
			day++;
		}

		return -1;

	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_m = Integer.parseInt(tokens.nextToken());
		_n = Integer.parseInt(tokens.nextToken());

		_notRipedNum = 0;
		_riped = new LinkedList<>();
		_isVisited = new boolean[_n][_m];
		_map = new int[_n][_m];
		for (int i = 0; i < _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < _m; j++) {
				_map[i][j] = Integer.parseInt(tokens.nextToken());
				if (_map[i][j] == 0) {
					_notRipedNum++;
				} else if (_map[i][j] == 1) {
					_notRipedNum++;
					_riped.offer(new Point(i, j));
					_isVisited[i][j] = true;
				}
			}
		}

		output.append(bfs());

		System.out.println(output);

	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

}
