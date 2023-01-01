package bj.g2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G2_03109 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] deltas = { { -1, 1 }, { 0, 1 }, { 1, 1 } }; // 우상 우 우하

	static int _r, _c, _answer;
	static char[][] _map;
	static boolean _possible;
	static boolean[][] _isVisited;

	static boolean isIn(int r, int c) {
		return r >= 0 && r < _r && c >= 0 && c < _c;
	}

	static void pipePossible(int r, int c) {
		if (c == _c - 1) {
			_possible = true;
			_answer++;
			return;
		}
		for (int i = 0; i < 3; i++) {
			int nr = r + deltas[i][0];
			int nc = c + deltas[i][1];
			if (isIn(nr, nc) && _map[nr][nc] == '.' && !_isVisited[nr][nc]) {
				_map[nr][nc] = 'x';
				_isVisited[nr][nc] = true;
				pipePossible(nr, nc);
				if (_possible) {
					return;
				} else {
					_map[nr][nc] = '.';
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {

		tokens = new StringTokenizer(input.readLine());

		_r = Integer.parseInt(tokens.nextToken());
		_c = Integer.parseInt(tokens.nextToken());
		_map = new char[_r][_c];
		for (int i = 0; i < _r; i++) {
			_map[i] = input.readLine().toCharArray();
		}

		_answer = 0;
		_isVisited = new boolean[_r][_c];
		for (int i = 0; i < _r; i++) {
			_possible = false;
			pipePossible(i, 0);
		}

		output.append(_answer);
		System.out.print(output);
	}

}
