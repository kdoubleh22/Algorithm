package swea.d5;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D5_01247 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _t, _n, _min;
	static Point _company, _home;
	static Point[] _customers;

	static int findDistance(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}

	static void perm(int cnt, int distance, int[] result, boolean[] isVisited) {
		if (distance > _min) {
			return;
		}

		if (cnt == _n) {
			distance = distance + findDistance(_home, _customers[result[_n - 1]]);
			if (distance < _min) {
				_min = distance;
			}
			return;
		}

		for (int i = 0; i < _n; i++) {
			if (!isVisited[i]) {
				isVisited[i] = true;
				result[cnt] = i;
				int newDistance = 12341234;
				if (cnt == 0) {
					newDistance = findDistance(_company, _customers[i]);
				} else {
					newDistance = findDistance(_customers[result[cnt - 1]], _customers[i]);
				}
				perm(cnt + 1, distance + newDistance, result, isVisited);
				isVisited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
//		input = new BufferedReader(new FileReader("./io/swea01247input.txt"));

		_t = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= _t; tc++) {
			_n = Integer.parseInt(input.readLine());
			tokens = new StringTokenizer(input.readLine());
			_company = new Point(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
			_home = new Point(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
			_customers = new Point[_n];
			for (int i = 0; i < _n; i++) {
				_customers[i] = new Point(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
			} // 입력 끝.

			_min = Integer.MAX_VALUE;
			perm(0, 0, new int[_n], new boolean[_n]);

			output.append("#").append(tc).append(" ").append(_min).append("\n");
		}
		System.out.print(output);
	}

}
