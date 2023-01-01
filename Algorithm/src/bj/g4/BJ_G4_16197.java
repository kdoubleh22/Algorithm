package bj.g4;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G4_16197 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m, _answer;
	static char[][] _board;
	static List<Point> _coins;

	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static class TwoCoin {
		int r1, c1, r2, c2, cnt;

		public TwoCoin(int r1, int c1, int r2, int c2, int cnt) {
			super();
			this.r1 = r1;
			this.c1 = c1;
			this.r2 = r2;
			this.c2 = c2;
			this.cnt = cnt;
		}

	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < _n && c >= 0 && c < _m;
	}

	static void bfs(TwoCoin twoCoin) {
		Queue<TwoCoin> q = new LinkedList<>();
		q.offer(twoCoin);
		boolean[][] isVisited1 = new boolean[_n][_m];
		isVisited1[twoCoin.r1][twoCoin.c1] = true;
		while (!q.isEmpty()) {
			TwoCoin polled = q.poll();
			int r1 = polled.r1;
			int c1 = polled.c1;
			int r2 = polled.r2;
			int c2 = polled.c2;
			int cnt = polled.cnt;
			if (cnt == 10 || cnt == _answer) {
				return;
			}
			for (int i = 0; i < 4; i++) {
				int nr1 = r1 + deltas[i][0];
				int nc1 = c1 + deltas[i][1];
				int nr2 = r2 + deltas[i][0];
				int nc2 = c2 + deltas[i][1];
				if (isIn(nr1, nc1) != isIn(nr2, nc2)) {
					_answer = Math.max(_answer, cnt + 1);
					return;
				} else if (isIn(nr1, nc1) == true && isIn(nr2, nc2) == true) {
					if (!isVisited1[nr1][nc1]) {
						if (_board[nr1][nc1] == '#') { // 벽이면 제자리.
							nr1 = r1;
							nc1 = c1;
						}
						if (_board[nr2][nc2] == '#') {
							nr2 = r2;
							nc2 = c2;
						}
						if (nr1 == nr2 && nc1 == nc2) { // 겹치면.
							continue;
						}
						q.offer(new TwoCoin(nr1, nc1, nr2, nc2, cnt + 1));
						isVisited1[nr1][nc1] = true;
					}
				} else { // 둘 다 떨어지면, pass.
					continue;
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());

		_board = new char[_n][_m];
		_coins = new ArrayList<Point>();
		for (int i = 0; i < _n; i++) {
			String line = input.readLine();
			for (int j = 0; j < _m; j++) {
				_board[i][j] = line.charAt(j);
				if (_board[i][j] == 'o') {
					_coins.add(new Point(i, j));
				}
			}
		} // 입력 끝.

		_answer = -1;
		bfs(new TwoCoin(_coins.get(0).x, _coins.get(0).y, _coins.get(1).x, _coins.get(1).y, 0));
		bfs(new TwoCoin(_coins.get(1).x, _coins.get(1).y, _coins.get(0).x, _coins.get(0).y, 0));

		output.append(_answer);
		System.out.println(output);
	}

}
