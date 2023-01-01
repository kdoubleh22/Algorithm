package bj.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * Arrays.fill은 primitve타입에서만 쓰자. 아니면 다같이 바뀜
 */

public class BJ_G2_19237 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] deltas = { {}, { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int _n, _m, _k, _cnt;
	static Block[][] _map;
	static int[][] _locations;
	static int[][][] _priorities;
	static Shark[] _sharks;

	static void removeSmell() {
		for (int i = 0; i < _n; i++) {
			for (int j = 0; j < _n; j++) {
				if (_map[i][j].t > 0) {
					_map[i][j].t -= 1;
					if (_map[i][j].t == 0) {
						// 냄새 지움
						_map[i][j].t = -1;
						_map[i][j].n = -1;
					}
				}
			}
		}
	}

	static void smell() {
		for (int i = 1; i <= _m; i++) {
			if (_sharks[i] != null) {
				Shark s = _sharks[i];
				_map[s.r][s.c].n = i;
				_map[s.r][s.c].t = _k;
			}
		}
	}

	static boolean isIn(int r, int c) {
		return 0 <= r && r < _n && 0 <= c && c < _n;
	}

	static void move() {
		int[][] newLocations = new int[_n][_n];

		outer: for (int i = 1; i <= _m; i++) {

			Shark s = _sharks[i];
			if (s != null) {
				int r = s.r;
				int c = s.c;
				int dir = s.d;
				// 냄새가 없는 칸이 있을경우,
				for (int d = 0; d < 4; d++) {
					int nr = r + deltas[s.priorities[dir][d]][0];
					int nc = c + deltas[s.priorities[dir][d]][1];
					// 냄새가 없는 칸을 찾으면
					if (isIn(nr, nc) && _map[nr][nc].n == -1) {
						// 아무도 없으면
						if (newLocations[nr][nc] == 0) {
							newLocations[nr][nc] = i;
							// 상어 위치 업데이트
							s.r = nr;
							s.c = nc;
						} else { // 누군가 있으면 나보다 작은 상어라 못 들어감 cnt만 --
							_cnt--;
							// 상어 먹힘 처리
							_sharks[i] = null;
						}
						// 방향 업데이트
						s.d = s.priorities[dir][d];
						continue outer;
					}
				}
				// 여기까지 오면 냄새 없는 칸이 없다는거임
				for (int d = 0; d < 4; d++) {
					int nr = r + deltas[s.priorities[dir][d]][0];
					int nc = c + deltas[s.priorities[dir][d]][1];
					if (isIn(nr, nc) && _map[nr][nc].n == i) {
						// 아무도 없으면
						if (newLocations[nr][nc] == 0) {
							newLocations[nr][nc] = i;
							// 상어 위치 업데이트
							s.r = nr;
							s.c = nc;
						} else { // 누군가 있으면 나보다 작은 상어라 못 들어감 cnt만 --
							_cnt--;
							// 상어 먹힘 처리
							_sharks[i] = null;
						}
						s.d = s.priorities[dir][d];
						continue outer;
					}
				}
			}

		} // for outer

		// 위치 없데이트
		_locations = newLocations;
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());
		_k = Integer.parseInt(tokens.nextToken());

		_sharks = new Shark[_m + 1];
		_locations = new int[_n][_n];
		for (int i = 0; i < _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < _n; j++) {
				_locations[i][j] = Integer.parseInt(tokens.nextToken());
				if (_locations[i][j] != 0) {
					_sharks[_locations[i][j]] = new Shark(i, j, 0, new int[5][4]);
				}
			}
		}

		tokens = new StringTokenizer(input.readLine());
		for (int i = 1; i <= _m; i++) {
			_sharks[i].d = Integer.parseInt(tokens.nextToken());
		}

		for (int i = 1; i <= _m; i++) {
			for (int j = 1; j <= 4; j++) {
				tokens = new StringTokenizer(input.readLine());
				for (int k = 0; k < 4; k++) {
					_sharks[i].priorities[j][k] = Integer.parseInt(tokens.nextToken());
				}
			}
		}

		// 초기화
		_map = new Block[_n][_n];
//		for(Block[] row:_map) {
//			Arrays.fill(row, new Block(-1,-1));
//		}
		for (int i = 0; i < _n; i++) {
			for (int j = 0; j < _n; j++) {
				_map[i][j] = new Block(-1, -1);
			}
		}
		_cnt = _m;

		smell();

		int answer = -1;
		for (int t = 1; t <= 1000; t++) {

			// 이동
			move();

			// 1마리면 종료
			if (_cnt == 1) {
				answer = t;
				break;
			}

			// 냄새 없애기
			removeSmell();

			// 냄새 퍼뜨리기
			smell();

		}

		System.out.println(answer);

	}

	static class Block {
		int n; // number
		int t; // time

		@Override
		public String toString() {
			return "Block [n=" + n + ", t=" + t + "]";
		}

		public Block(int n, int t) {
			super();
			this.n = n;
			this.t = t;
		}
	}

	static class Shark {
		int r, c, d; // row col dir
		int[][] priorities;

		public Shark(int r, int c, int d, int[][] priorities) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
			this.priorities = priorities;
		}

		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", d=" + d + ", priorities=" + Arrays.toString(priorities) + "]";
		}

	}

}
