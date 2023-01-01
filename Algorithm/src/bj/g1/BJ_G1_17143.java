package bj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 
 * @author SSAFY
 * @memo 굳이 map을 안 쓰고, 그냥 새로운 2차원배열에 넣으면서 확인하는게 편할 것 같다.
 */

public class BJ_G1_17143 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _r, _c, _m, _answer;
	static Shark[][] _map;
	static Map<Point, Shark> _hashMap, _temp;

	static int[][] deltas = { {}, { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	static void grab(int col) {
		for (int r = 1; r <= _r; r++) {
			if (_map[r][col] != null) {
				_answer += _map[r][col].z;
				_map[r][col] = null;
				_hashMap.remove(new Point(r, col));
				return;
			}
		}
	}

	static boolean isIn(int r, int c) {
		return 1 <= r && r <= _r && 1 <= c && c <= _c;
	}

	static int[] findNext(int row, int col, int dir, int speed) {
		int[] result = new int[4];

		int nr = row + speed * deltas[dir][0];
		int nc = col + speed * deltas[dir][1];

		if (nr < 1) {
			nr = 1 + (1 - nr);
			dir = 2;
		}
		if (nr > _r) {
			nr = _r - (nr - _r);
			dir = 1;
		}
		if (nr < 1) {
			nr = 1 + (1 - nr);
			dir = 2;
		}

		if (nc < 1) {
			nc = 1 + (1 - nc);
			dir = 3;
		}
		if (nc > _c) {
			nc = _c - (nc - _c);
			dir = 4;
		}
		if (nc < 1) {
			nc = 1 + (1 - nc);
			dir = 3;
		}

		row = nr;
		col = nc;

//		if (dir == 1) { // 위
//			int nr = row + speed * deltas[1][0];
//			if (nr < 1) {
//				nr = 1 + (1 - nr);
//				dir = 2;
//			}
//			if (nr > _r) {
//				nr = _r - (nr - _r);
//				dir = 1;
//			}
//			row = nr;
//		} else if (dir == 2) { // 아래
//			int nr = row + speed * deltas[2][0];
//			if (nr > _r) {
//				nr = _r - (nr - _r);
//				dir = 1;
//			}
//			if (nr < 1) {
//				nr = 1 + (1 - nr);
//				dir = 2;
//			}
//			row = nr;
//		} else if (dir == 3) { // 오른쪽
//			int nc = col + speed * deltas[3][1];
//			if (nc > _c) {
//				nc = _c - (nc - _c);
//				dir = 4;
//			}
//			if (nc < 1) {
//				nc = 1 + (1 - nc);
//				dir = 3;
//			}
//			col = nc;
//		} else if (dir == 4) { // 왼쪽
//			int nc = col + speed * deltas[4][1];
//			if (nc < 1) {
//				nc = 1 + (1 - nc);
//				dir = 3;
//			}
//			if (nc > _c) {
//				nc = _c - (nc - _c);
//				dir = 4;
//			}
//			col = nc;
//		}

//		for (int i = 0; i < speed; i++) {
//			int nr = row + deltas[dir][0];
//			int nc = col + deltas[dir][1];
//			if (!isIn(nr, nc)) { // 맵 밖으로 나가면 뒤로 한 칸.
//				nr = row - deltas[dir][0];
//				nc = col - deltas[dir][1];
//				// 방향 전환.
//				if (dir == 1) {
//					dir = 2;
//				} else if (dir == 2) {
//					dir = 1;
//				} else if (dir == 3) {
//					dir = 4;
//				} else if (dir == 4) {
//					dir = 3;
//				}
//			}
//			row = nr;
//			col = nc;
//		}
		result[0] = row;
		result[1] = col;
		result[2] = dir;
		result[3] = speed;

		return result;
	}

	static void move() {
		for (Point p : _hashMap.keySet()) {
			Shark s = _hashMap.get(p);
			// 맵에서 빼주고
			_map[p.r][p.c] = null;
			int[] result = findNext(p.r, p.c, s.d, s.s);
			// 있는거라면 크기 비교 후 작은거 삭제
			if (_temp.containsKey(new Point(result[0], result[1]))) {
				if (_temp.get(new Point(result[0], result[1])).z < s.z) {
					_temp.replace(new Point(result[0], result[1]), new Shark(result[3], result[2], s.z));
				}
			} else {
				_temp.put(new Point(result[0], result[1]), new Shark(result[3], result[2], s.z));
			}
		}
		// 다시 맵에다 넣어주고,
		for (Point p : _temp.keySet()) {
			Shark s = _temp.get(p);
			_map[p.r][p.c] = new Shark(s.s, s.d, s.z);
		}
		// _hashMap을 newHashMap으로 교체.
		_hashMap.clear();
		Map<Point, Shark> swap = _hashMap;
		_hashMap = _temp;
		_temp = swap;

	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_r = Integer.parseInt(tokens.nextToken());
		_c = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());

		_map = new Shark[_r + 1][_c + 1];
		_hashMap = new HashMap<>();
		_temp = new HashMap<>();
		for (int i = 0; i < _m; i++) {
			tokens = new StringTokenizer(input.readLine());
			int r = Integer.parseInt(tokens.nextToken());
			int c = Integer.parseInt(tokens.nextToken());
			int s = Integer.parseInt(tokens.nextToken());
			int d = Integer.parseInt(tokens.nextToken());
			int z = Integer.parseInt(tokens.nextToken());

			if (d == 1 || d == 2) {
				s = s % (2 * _r - 2);
			} else {
				s = s % (2 * _c - 2);
			}

			_map[r][c] = new Shark(s, d, z);
			_hashMap.put(new Point(r, c), new Shark(s, d, z));

		} // 입력 끝.

//		for(Shark[] row:_map) {
//			System.out.println(Arrays.toString(row));
//		}

//		System.out.println(_hashMap);

		_answer = 0;
		for (int kingCol = 1; kingCol <= _c; kingCol++) {
			grab(kingCol);
			move();
		}
		System.out.println(_answer);

	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + r;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (c != other.c)
				return false;
			if (r != other.r)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}

	}

	static class Shark {
		int s, d, z;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + d;
			result = prime * result + s;
			result = prime * result + z;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Shark other = (Shark) obj;
			if (d != other.d)
				return false;
			if (s != other.s)
				return false;
			if (z != other.z)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Shark [s=" + s + ", d=" + d + ", z=" + z + "]";
		}

		public Shark(int s, int d, int z) {
			this.s = s;
			this.d = d;
			this.z = z;
		}

	}

}
