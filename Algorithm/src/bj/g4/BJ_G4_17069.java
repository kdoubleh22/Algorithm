package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G4_17069 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static int[][] _map;
	static long[][][] _methods;

	static boolean isIn(int r, int c) {
		return 1 <= r && r <= _n && 1 <= c && c <= _n;
	}

	static boolean isRightEmpty(int r, int c) {
		return isIn(r, c + 1) && (_map[r][c + 1] == 0);
	}

	static boolean isDownEmpty(int r, int c) {
		return isIn(r + 1, c) && (_map[r + 1][c] == 0);
	}

	static boolean isDiagonalEmpty(int r, int c) {
		return isIn(r + 1, c) && isIn(r, c + 1) && isIn(r + 1, c + 1) && (_map[r + 1][c] == 0) && (_map[r][c + 1] == 0)
				&& (_map[r + 1][c + 1] == 0);
	}

	static void bfs() {
		boolean[][] isVisited = new boolean[_n + 1][_n + 1];
		PriorityQueue<Pipe> q = new PriorityQueue<>();
		q.offer(new Pipe(0, 1, 2));
		isVisited[1][2] = true;

		while (!q.isEmpty()) {
			Pipe polled = q.poll();
			int dir = polled.dir;
			int r = polled.r;
			int c = polled.c;

//			System.out.println("r:" + r + " c:" + c);

			if (_methods[r][c][2] != 0) { // 대각선
				if (isRightEmpty(r, c)) { // 가로
					_methods[r][c + 1][0] += _methods[r][c][2];
					if (!isVisited[r][c + 1]) {
						q.offer(new Pipe(0, r, c + 1));
						isVisited[r][c + 1] = true;
					}
				}
				if (isDownEmpty(r, c)) { // 세로
					_methods[r + 1][c][1] += _methods[r][c][2];
					if (!isVisited[r + 1][c]) {
						q.offer(new Pipe(1, r + 1, c));
						isVisited[r + 1][c] = true;
					}
				}
				if (isDiagonalEmpty(r, c)) { // 대각선
					_methods[r + 1][c + 1][2] += _methods[r][c][2];
					if (!isVisited[r + 1][c + 1]) {
						q.offer(new Pipe(2, r + 1, c + 1));
						isVisited[r + 1][c + 1] = true;
					}
				}
			}

			if (_methods[r][c][0] != 0) { // 가로
				if (isRightEmpty(r, c)) { // 가로
					_methods[r][c + 1][0] += _methods[r][c][0];
					if (!isVisited[r][c + 1]) {
						q.offer(new Pipe(0, r, c + 1));
						isVisited[r][c + 1] = true;
					}
				}
				if (isDiagonalEmpty(r, c)) { // 대각선
					_methods[r + 1][c + 1][2] += _methods[r][c][0];
					if (!isVisited[r + 1][c + 1]) {
						q.offer(new Pipe(2, r + 1, c + 1));
						isVisited[r + 1][c + 1] = true;
					}
				}
			}

			if (_methods[r][c][1] != 0) { // 세로
				if (isDownEmpty(r, c)) { // 세로
					_methods[r + 1][c][1] += _methods[r][c][1];
					if (!isVisited[r + 1][c]) {
						q.offer(new Pipe(1, r + 1, c));
						isVisited[r + 1][c] = true;
					}
				}
				if (isDiagonalEmpty(r, c)) { // 대각선
					_methods[r + 1][c + 1][2] += _methods[r][c][1];
					if (!isVisited[r + 1][c + 1]) {
						q.offer(new Pipe(2, r + 1, c + 1));
						isVisited[r + 1][c + 1] = true;
					}
				}
			}

//			for (int i = 1; i <= _n; i++) {
//				for (int j = 1; j <= _n; j++) {
//					for (int k = 0; k < 3; k++) {
//						System.out.print(_methods[i][j][k] + " ");
//					}
//					System.out.print("   ");
//				}
//				System.out.println();
//			}
//			System.out.println("==========================");

		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_map = new int[_n + 1][_n + 1];
		_methods = new long[_n + 1][_n + 1][3]; // 0:가로, 1:세로, 2:대각선
		_methods[1][2][0] = 1;

		for (int r = 1; r <= _n; r++) {
			tokens = new StringTokenizer(input.readLine());
			for (int c = 1; c <= _n; c++) {
				_map[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}

		bfs();

		long answer = _methods[_n][_n][0] + _methods[_n][_n][1] + _methods[_n][_n][2];

//		for (int i = 1; i <= _n; i++) {
//			for (int j = 1; j <= _n; j++) {
//				for (int k = 0; k < 3; k++) {
//					System.out.print(_methods[i][j][k] + " ");
//				}
//				System.out.print("   ");
//			}
//			System.out.println();
//		}
//
		System.out.println(answer);

	}

	static class Pipe implements Comparable<Pipe> {
		int dir, r, c;

		public Pipe(int dir, int r, int c) {
			this.dir = dir;
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Pipe o) {
			if (this.r == o.r) {
				return Integer.compare(this.c, o.c);
			}
			return Integer.compare(this.r, o.r);
		}

	}

}
