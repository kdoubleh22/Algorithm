package bj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G3_16236 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static int _n, _sX, _sY, _sSize, _time, _eatCnt;
	static int[][] _map;

	static boolean isIn(int r, int c) {
		return r >= 0 && r < _n && c >= 0 && c < _n;
	}

	static boolean bfs() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] isVisited = new boolean[_n][_n];

		q.offer(new Point(_sX, _sY));
		isVisited[_sX][_sY] = true;

		int time = 0;
		while (!q.isEmpty()) {
			List<Point> canEat = new ArrayList<>();
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point cur = q.poll();
				int r = cur.r;
				int c = cur.c;
				if (_map[r][c] != 0 && _map[r][c] != 9) { // 물고기라면,
					if (_map[r][c] < _sSize) {// 아기상어보다 작다면,
						canEat.add(new Point(r, c));
					}
				}
				for (int j = 0; j < 4; j++) {
					int nr = r + deltas[j][0];
					int nc = c + deltas[j][1];
					if (isIn(nr, nc) && _map[nr][nc] <= _sSize && !isVisited[nr][nc]) {
						q.offer(new Point(nr, nc));
						isVisited[nr][nc] = true;
					}
				}

			}
			if (!canEat.isEmpty()) {
				Collections.sort(canEat);
				Point eatNow = canEat.get(0);
				int r = eatNow.r;
				int c = eatNow.c;
				_map[r][c] = 9; // 이동해서 먹고,
				_map[_sX][_sY] = 0; // 원래 자리는 비워주고,
				// 상어 위치정보 업데이트
				_sX = r;
				_sY = c;
				if (++_eatCnt == _sSize) { // 먹은 숫자가 상어 사이즈랑 같으면,
					_sSize += 1; // 상어 크기 키워주고,
					_eatCnt = 0; // 먹은횟수 초기화
				}
				_time += time;
				return true;
			}
			time++;
		}

		return false;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_map = new int[_n][_n];
		for (int i = 0; i < _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < _n; j++) {
				_map[i][j] = Integer.parseInt(tokens.nextToken());
				if (_map[i][j] == 9) {
					_sX = i;
					_sY = j;
				}
			}
		}

		_time = 0;
		_sSize = 2;
		_eatCnt = 0;
		while (true) {
			if (bfs()) { // 잡아 먹을 수 있으면,

			} else { // 잡아먹을게 없다면,
				break;
			}
		}

		output.append(_time);
		System.out.println(output);

	}

	static class Point implements Comparable<Point> {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Point o) {
			if (this.r == o.r) {
				return Integer.compare(this.c, o.c);
			} else {
				return Integer.compare(this.r, o.r);
			}
		}

	}

}