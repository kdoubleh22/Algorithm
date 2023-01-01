package bj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @see https://www.acmicpc.net/problem/1992
 * @performance 12036 KB 84 ms
 * @category #분할 정복 #재귀
 * @memo 변수 헷갈리지 않기.
 * 
 */

public class BJ_S1_01992 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static char[][] _video;

	static boolean checkAllSame(int n, int r, int c) {
		char start = _video[r][c];
		for (int i = r; i < r + n; i++) {
			for (int j = c; j < c + n; j++) {
				if (_video[i][j] != start) {
					return false;
				}
			}
		}
		return true;
	}

	static void quadTree(int n, int r, int c) {
		if (checkAllSame(n, r, c)) {
			output.append(_video[r][c]);
			return;
		}
		output.append("(");
		if (checkAllSame(n / 2, r, c)) {
			output.append(_video[r][c]);
		} else {
			quadTree(n / 2, r, c);
		}
		if (checkAllSame(n / 2, r, c + n / 2)) {
			output.append(_video[r][c + n / 2]);
		} else {
			quadTree(n / 2, r, c + n / 2);
		}
		if (checkAllSame(n / 2, r + n / 2, c)) {
			output.append(_video[r + n / 2][c]);
		} else {
			quadTree(n / 2, r + n / 2, c);
		}
		if (checkAllSame(n / 2, r + n / 2, c + n / 2)) {
			output.append(_video[r + n / 2][c + n / 2]);
		} else {
			quadTree(n / 2, r + n / 2, c + n / 2);
		}
		output.append(")");

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());
		_video = new char[_n][_n];
		for (int i = 0; i < _n; i++) {
			_video[i] = input.readLine().toCharArray();
		}

		quadTree(_n, 0, 0);

		System.out.print(output);
	}

}
