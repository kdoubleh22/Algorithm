package bj.g2;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BJ_G2_17136 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] _paper;
//	static int[][] _copiedPaper;
	static int[][] _maxPaper;
	static int _answer, _cnt, _total;
	static int[] _paperCnt;
	static Set<Point> _locations;

	static boolean isIn(int r, int c) {
		return 0 <= r && r < 10 && 0 <= c && c < 10;
	}

	static int check(int r, int c) {
		int result = 1;
		outer: for (int k = 1; k < 5; k++) {
			for (int i = 0; i < k; i++) {
				if (!isIn(r + k, c + i) || _paper[r + k][c + i] == 0) {
					break outer;
				}
				if (!isIn(r + i, c + k) || _paper[r + i][c + k] == 0) {
					break outer;
				}
			}
			result = k + 1;
		}

		return result;
	}

	// 색종이를 붙인다.
	static void attach(int r, int c, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				_paper[i][j] = 0;
			}
		}
	}

	// 색종이를 뗀다.
	static void detach(int r, int c, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				_paper[i][j] = 1;
			}
		}
	}

	static void recur(int r, int c) {
		// 범위 초과.
		if (r == 10) {
			return;
		}

		// 색종이를 붙일 수 없는 곳이면, 다음 칸으로 넘김.
		if (_paper[r][c] == 0) {
			// 마지막 열이면,
			if (c + 1 == 10) {
				recur(r + 1, 0);
			} else {
				recur(r, c + 1);
			}
		}
		// 붙일 수 있는 곳이면,다 붙여본다.
		else {
			for (int i = 1; i <= _maxPaper[r][c]; i++) {
				if (_paperCnt[i] != 0) { // 색종이가 남아있으면,
					attach(r, c, i); // 붙인다.
					_cnt += 1;
					_total -= i * i;
					_paperCnt[i] -= 1;
					if (_total == 0) {
						_answer = Math.min(_answer, _cnt);
					} else {
						if (c + 1 == 10) {
							recur(r + 1, 0);
						} else {
							recur(r, c + 1);
						}
					}
					// 원상복귀.
					detach(r, c, i);
					_cnt -= 1;
					_total += i * i;
					_paperCnt[i] += 1;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		_locations = new TreeSet<>();
		_paper = new int[10][10];
//		_copiedPaper = new int[10][10];

		for (int i = 0; i < 10; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < 10; j++) {
				_paper[i][j] = Integer.parseInt(tokens.nextToken());
//				_copiedPaper[i][j] = _paper[i][j];
			}
		}

		_maxPaper = new int[10][10];

		_total = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (_paper[i][j] == 1) {
					_maxPaper[i][j] = check(i, j);
					_total += 1;
					_locations.add(new Point(i, j));
				}
			}
		}

//		for (int i = 0; i < 10; i++) {
//			System.out.println(Arrays.toString(_maxPaper[i]));
//		}

		_answer = Integer.MAX_VALUE;

		// 전부 0이었을 때 예외처리. 이 방법 좋진 않음.
		if (_total == 0) {
			_answer = 0;
		}

		_cnt = 0;
		_paperCnt = new int[6];
		for (int i = 1; i <= 5; i++) {
			_paperCnt[i] = 5;
		}

//		Point polled = _locations.recur(0, 0); // 붙일 수 있는 모든 경우의 수 계산

		if (_answer == Integer.MAX_VALUE) {
			output.append(-1);
		} else {
			output.append(_answer);
		}

		System.out.println(output);
	}

	static class Point {
		int r, c;

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

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

}
