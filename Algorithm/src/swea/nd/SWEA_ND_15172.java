package swea.nd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author hh
 * @time 13:23~14:10
 */

public class SWEA_ND_15172 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _answer;
	static int[][] _map;
	static List<Point> _cnmsList; // customers and monsters
	static Point[] _cnmsArr;

	static int findDistance(int r1, int c1, int r2, int c2) {
		return Math.abs(r1 - r2) + Math.abs(c1 - c2);
	}

	static void perm(int cnt, boolean[] mVisited, boolean[] cVisited, int sum, int r, int c) {

		if (sum >= _answer) {
			return;
		}

		if (cnt == _cnmsArr.length) {
			_answer = Math.min(_answer, sum);
			return;
		}

		for (int i = 0; i < _cnmsArr.length; i++) {
			Point cur = _cnmsArr[i];
			if (cur.n > 0 && !mVisited[cur.n]) { // 몬스터면 그냥 진행해도 됨.
				mVisited[cur.n] = true; // 방문처리
				perm(cnt + 1, mVisited, cVisited, sum + findDistance(cur.r, cur.c, r, c), cur.r, cur.c);
				mVisited[cur.n] = false;
			} else if (cur.n < 0 && !cVisited[-1 * cur.n] && mVisited[-1 * cur.n]) { // 고객이면, 전에 몬스터가 처리됐는지 확인.
				cVisited[-1 * cur.n] = true; // 방문처리
				perm(cnt + 1, mVisited, cVisited, sum + findDistance(cur.r, cur.c, r, c), cur.r, cur.c);
				cVisited[-1 * cur.n] = false;
			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {

//		input = new BufferedReader(new FileReader("./io/swea15172input.txt"));

		int t = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= t; tc++) {
			_n = Integer.parseInt(input.readLine());

			_map = new int[_n][_n];
			_cnmsList = new ArrayList<>();
			for (int i = 0; i < _n; i++) {
				tokens = new StringTokenizer(input.readLine());
				for (int j = 0; j < _n; j++) {
					_map[i][j] = Integer.parseInt(tokens.nextToken());
					if (_map[i][j] != 0) {
						_cnmsList.add(new Point(i, j, _map[i][j]));
					}
				}
			} // 입력.

			// List를 Array로 변환.
			_cnmsArr = new Point[_cnmsList.size()];
			for (int i = 0; i < _cnmsList.size(); i++) {
				_cnmsArr[i] = _cnmsList.get(i);
			}

			_answer = Integer.MAX_VALUE;
			perm(0, new boolean[(_cnmsList.size() / 2) + 1], new boolean[(_cnmsList.size() / 2) + 1], 0, 0, 0);

			output.append("#").append(tc).append(" ").append(_answer).append("\n");

		} // tc

		System.out.println(output);

	}

	static class Point {
		int r, c, n; // 

		public Point(int r, int c, int n) {
			this.r = r;
			this.c = c;
			this.n = n;
		}

		@Override
		public String toString() {
			return "[r=" + r + ", c=" + c + ", n=" + n + "]";
		}

	}

}
