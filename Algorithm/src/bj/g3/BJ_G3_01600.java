package bj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G3_01600 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] _mDeltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int[][] _hDeltas = { { -2, -1 }, { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, -1 }, { 2, 1 }, { -1, -2 },
			{ 1, -2 } };

	static int _k, _w, _h;
	static int[][] _map;

	static boolean isIn(int r, int c) {
		return r >= 0 && r < _h && c >= 0 && c < _w;
	}

	static int bfs() {
		Queue<Point> q = new LinkedList<>();
		boolean[][][] visited = new boolean[_h][_w][_k + 1]; // 0은 원숭이,1은 말

		q.add(new Point(0, 0, 0));
		visited[0][0][0] = true;

		int cnt = 0;
		while (!q.isEmpty()) {
			int size = q.size();
//			System.out.println("cnt:" + cnt);
			for (int i = 0; i < size; i++) {
				Point cur = q.poll();

//				System.out.println("r:" + cur.r + ",c:" + cur.c);

				if (cur.r == _h - 1 && cur.c == _w - 1) {
					return cnt;
				}

				for (int d = 0; d < 4; d++) {
					int nr = cur.r + _mDeltas[d][0];
					int nc = cur.c + _mDeltas[d][1];
					if (isIn(nr, nc) && !visited[nr][nc][cur.hCnt] && _map[nr][nc] == 0) {
						q.offer(new Point(nr, nc, cur.hCnt));
						visited[nr][nc][cur.hCnt] = true;
					}
				}

				if (cur.hCnt < _k) {
					for (int d = 0; d < 8; d++) {
						int nr = cur.r + _hDeltas[d][0];
						int nc = cur.c + _hDeltas[d][1];
						if (isIn(nr, nc) && !visited[nr][nc][cur.hCnt + 1] && _map[nr][nc] == 0) {
							q.offer(new Point(nr, nc, cur.hCnt + 1));
							visited[nr][nc][cur.hCnt + 1] = true;
						}
					}
				}

			}
			cnt++;
		}

		return -1;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_k = Integer.parseInt(input.readLine());
		tokens = new StringTokenizer(input.readLine());
		_w = Integer.parseInt(tokens.nextToken());
		_h = Integer.parseInt(tokens.nextToken());

		_map = new int[_h][_w];
		for (int i = 0; i < _h; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < _w; j++) {
				_map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		} // 입력

		int answer = bfs();

		output.append(answer);
		System.out.println(output);
	}

	static class Point {
		int r, c, hCnt; // horse count

		public Point(int r, int c, int hCnt) {
			this.r = r;
			this.c = c;
			this.hCnt = hCnt;
		}

	}

}
