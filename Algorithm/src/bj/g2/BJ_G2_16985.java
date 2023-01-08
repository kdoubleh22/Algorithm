package bj.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -문제
 * -풀이
 * 완탐: 판의방향*쌓는법*꼭짓점고르기 = (4^5)*5!*4 = 약 50만
 * 최악 시간복잡도: 50만*125 = 약 5000만?
 * 회전+순열
 */

public class BJ_G2_16985 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	// 아래층, 우, 하, 좌, 상, 위층
	static int[][] deltas = { { -1, 0, 0 }, { 0, 0, 1 }, { 0, 1, 0 }, { 0, 0, -1 }, { 0, -1, 0 }, { 1, 0, 0 } };

	static int[][][] _boards;
	static int _answer;

	// 오른쪽으로 90도 회전.
	static int[][] rotate(int[][] board) {
		int[][] result = new int[5][5];
		for (int c = 0; c < 5; c++) {
			for (int r = 4; r > -1; r--) {
				result[c][4 - r] = board[r][c];
			}
		}
		return result;
	}

	static boolean isIn(int f, int r, int c) {
		return 0 <= f && f < 5 && 0 <= r && r < 5 && 0 <= c && c < 5;
	}

	static void bfs(int[][][] result) {
		for (int floor = 0; floor < 5; floor += 4) {
			for (int row = 0; row < 5; row += 4) {
				for (int col = 0; col < 5; col += 4) {
					if (result[floor][row][col] == 1) {
						Queue<Point> q = new ArrayDeque<>();
						boolean[][][] visited = new boolean[5][5][5]; // f,r,c

						q.offer(new Point(floor, row, col, 0));
						visited[floor][row][col] = true;

						while (!q.isEmpty()) {
							Point cur = q.poll();
							int f = cur.f;
							int r = cur.r;
							int c = cur.c;
							int d = cur.d;

							if (Math.abs(f - floor) == 4 && Math.abs(r - row) == 4 && Math.abs(c - col) == 4) {
								_answer = Math.min(_answer, d);
								return;
							}

							for (int dir = 0; dir < 6; dir++) {
								int nf = f + deltas[dir][0];
								int nr = r + deltas[dir][1];
								int nc = c + deltas[dir][2];
								if (isIn(nf, nr, nc) && result[nf][nr][nc] == 1 && !visited[nf][nr][nc]) {
									q.offer(new Point(nf, nr, nc, d + 1));
									visited[nf][nr][nc] = true;
								}
							}
						}
					}
				}
			}
		}

	}

	static void perm(int cnt, int[][][] result, boolean[] visited) {
		if (cnt == 5) {
			bfs(result);
			return;
		}

		for (int i = 0; i < 5; i++) {
			if (!visited[i]) {
				visited[i] = true;
				result[cnt] = _boards[i];
				perm(cnt + 1, result, visited);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		_boards = new int[5][5][5];

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				tokens = new StringTokenizer(input.readLine());
				for (int k = 0; k < 5; k++) {
					_boards[i][j][k] = Integer.parseInt(tokens.nextToken());
				}
			}
		} // 입력 끝.

//		// 입력 확인.
//		for (int i = 0; i < 5; i++) {
//			for (int j = 0; j < 5; j++) {
//				for (int k = 0; k < 5; k++) {
//					System.out.print(_boards[i][j][k] + " ");
//				}
//				System.out.println();
//			}
//		}

		_answer = Integer.MAX_VALUE;
		for (int first = 0; first < 4; first++, _boards[0] = rotate(_boards[0])) {
			for (int second = 0; second < 4; second++, _boards[1] = rotate(_boards[1])) {
				for (int third = 0; third < 4; third++, _boards[2] = rotate(_boards[2])) {
					for (int fourth = 0; fourth < 4; fourth++, _boards[3] = rotate(_boards[3])) {
						for (int fifth = 0; fifth < 4; fifth++, _boards[4] = rotate(_boards[4])) {
							perm(0, new int[5][5][5], new boolean[5]);
						}
					}
				}
			}
		}

		if (_answer == Integer.MAX_VALUE) {
			_answer = -1;
		}

		System.out.println(_answer);
	}

	static class Point {
		int f; // row
		int r; // col
		int c; // floor
		int d; // distance

		public Point(int f, int r, int c, int d) {
			super();
			this.f = f;
			this.r = r;
			this.c = c;
			this.d = d;
		}

	}

}
