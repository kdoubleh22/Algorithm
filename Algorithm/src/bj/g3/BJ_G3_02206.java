package bj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G3_02206 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int _n, _m, _answer;
	static char[][] _map;

	static boolean isIn(int r, int c) {
		return 0 <= r && r < _n && 0 <= c && c < _m;
	}

	static void bfs() {
		boolean[][][] isVisited = new boolean[_n][_m][2];
		Queue<Coordinate> q = new LinkedList<>();

		q.offer(new Coordinate(0, 0, 0));
		isVisited[0][0][0] = true;

		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				Coordinate polled = q.poll();
				int r = polled.r;
				int c = polled.c;
				int bCnt = polled.bCnt;
//				System.out.println("r:" + r + " c:" + c);
				if (r == _n - 1 && c == _m - 1) {
					return;
				}

				for (int d = 0; d < 4; d++) {
					int nr = r + deltas[d][0];
					int nc = c + deltas[d][1];
//					System.out.println("nr:" + nr + " nc:" + nc);
					if (isIn(nr, nc) && _map[nr][nc] == '0') { // 벽이 아니라면
						if (!isVisited[nr][nc][bCnt]) {
							q.offer(new Coordinate(nr, nc, bCnt));
							isVisited[nr][nc][bCnt] = true;
						}
					} else if (isIn(nr, nc) && _map[nr][nc] == '1') { // 벽이라면
						if (bCnt == 0 && !isVisited[nr][nc][1]) { // 부순 횟수가 0이면 부순다
							q.offer(new Coordinate(nr, nc, 1));
							isVisited[nr][nc][1] = true;
						}
					}
				}
//				for (int i = 0; i < _n; i++) {
//					for (int j = 0; j < _m; j++) {
//						System.out.print(isVisited[i][j][0] + " ");
//					}
//					System.out.println();
//				}
//				System.out.println("=============");
//				for (int i = 0; i < _n; i++) {
//					for (int j = 0; j < _m; j++) {
//						System.out.print(isVisited[i][j][1] + " ");
//					}
//					System.out.println();
//				}
//				System.out.println("=============");
			}
			_answer++;
		}
		_answer = -1;

	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());

		_map = new char[_n][];

		for (int i = 0; i < _n; i++) {
			_map[i] = input.readLine().toCharArray();
		}

		_answer = 1;
		bfs();

		System.out.println(_answer);

	}

	static class Coordinate {
		int r, c, bCnt; // bCnt: 벽을 부순 횟수.

		public Coordinate(int r, int c, int bCnt) {
			this.r = r;
			this.c = c;
			this.bCnt = bCnt;
		}

	}

}
