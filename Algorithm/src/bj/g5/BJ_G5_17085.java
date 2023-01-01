	package bj.g5;
	
	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.List;
	import java.util.StringTokenizer;
	
	public class BJ_G5_17085 {
	
		static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		static StringBuilder output = new StringBuilder();
		static StringTokenizer tokens;
	
		static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	
		static int _n, _m, _answer;
		static List<Point> _blanks;
		static char[][] _map;
	
		static boolean isIn(int r, int c) {
			return 0 <= r && r < _n && 0 <= c && c < _m;
		}
	
		static int put2(int r2, int c2, char[][] copy2) {
			if(copy2[r2][c2]=='.') {
				return -1;
			}
			int length = 1;
			outer: while (true) {
				for (int d = 0; d < 4; d++) {
					int nr = r2 + length * deltas[d][0];
					int nc = c2 + length * deltas[d][1];
					if (isIn(nr, nc) && copy2[nr][nc] == '#') {
						continue;
					} else {
						break outer;
					}
				}
				length++;
			}
	
			return 1 + (length - 1) * 4;
		}
	
		static void put1(int r1, int c1, int r2, int c2, char[][] copy) {
			
			int length = 1;
			outer: while (true) {
				// map copy
				char[][] copy2 = new char[_n][_m];
				for (int i = 0; i < _n; i++) {
					copy2[i] = Arrays.copyOf(copy[i], _m);
				}
				_answer = Integer.max(_answer, (1 + (length - 1) * 4) * put2(r2, c2, copy2));
	
				for (int d = 0; d < 4; d++) {
					int nr = r1 + length * deltas[d][0];
					int nc = c1 + length * deltas[d][1];
					if (isIn(nr, nc) && copy[nr][nc] == '#') {
						continue;
					} else {
						break outer;
					}
				}
				// 십자가 놓기
				for (int d = 0; d < 4; d++) {
					int nr = r1 + length * deltas[d][0];
					int nc = c1 + length * deltas[d][1];
					copy[nr][nc] = '.';
				}
	
				length++;
			}
		}
	
		static void putCross(int r1, int c1, int r2, int c2) {
			// map copy
			char[][] copy = new char[_n][_m];
			for (int i = 0; i < _n; i++) {
				copy[i] = Arrays.copyOf(_map[i], _m);
			}
			put1(r1, c1, r2, c2, copy);
	
		}
	
		public static void main(String[] args) throws IOException {
			tokens = new StringTokenizer(input.readLine());
			_n = Integer.parseInt(tokens.nextToken());
			_m = Integer.parseInt(tokens.nextToken());
	
			_blanks = new ArrayList<>();
			_map = new char[_n][];
			for (int i = 0; i < _n; i++) {
				_map[i] = input.readLine().toCharArray();
				for (int j = 0; j < _m; j++) {
					if (_map[i][j] == '#') {
						_blanks.add(new Point(i, j));
					}
				}
			}
	
			_answer = Integer.MIN_VALUE;
			for (int i = 0; i < _blanks.size() - 1; i++) {
				for (int j = i + 1; j < _blanks.size(); j++) {
					putCross(_blanks.get(i).r, _blanks.get(i).c, _blanks.get(j).r, _blanks.get(j).c);
				}
			}
	
			System.out.println(_answer);
		}
	
		static class Point {
			int r, c;
	
			public Point(int r, int c) {
				this.r = r;
				this.c = c;
			}
	
		}
	
	}
