package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @memo 인덱스를 1~r 1~n으로 잡으면 isIn도 그에 맞춰서 인덱싱해주기.
 *
 */

public class BJ_G4_17144 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static int _r, _c, _t;
	static int[][] _map;
	static List<Integer> _ac; // air cleaner

	static boolean isIn(int r, int c) {
		return r >= 1 && r < _r + 1 && c >= 1 && c < _c + 1;
	}

	static void spread() {
		int[][] plus = new int[_r + 1][_c + 1];
		for (int i = 1; i <= _r; i++) {
			for (int j = 1; j <= _c; j++) {
				if (_map[i][j] > 0) { // 미세먼지일 때
					for (int d = 0; d < 4; d++) {
						int ni = i + deltas[d][0];
						int nj = j + deltas[d][1];
						if (isIn(ni, nj) && _map[ni][nj] != -1) { // 맵안에 있고, 공기청정기가 아니면.
							plus[ni][nj] += _map[i][j] / 5;
							plus[i][j] -= _map[i][j] / 5;
						}
					}
				}
			}
		}
		for (int i = 1; i <= _r; i++) {
			for (int j = 1; j <= _c; j++) {
				_map[i][j] += plus[i][j];
			}
		}
	}

	static void wind() {
		int r1 = _ac.get(0);
		int r2 = _ac.get(1);

		// 위쪽 공기청정기
		for (int r = r1 - 2; r >= 1; r--) {
			_map[r + 1][1] = _map[r][1];
		}
		for (int c = 2; c <= _c; c++) {
			_map[1][c - 1] = _map[1][c];
		}
		for (int r = 2; r <= r1; r++) {
			_map[r - 1][_c] = _map[r][_c];
		}
		for (int c = _c - 1; c >= 2; c--) {
			_map[r1][c + 1] = _map[r1][c];
		}
		_map[r1][2] = 0;

		// 아래쪽 공기청정기
		for (int r = r2 + 2; r <= _r; r++) {
			_map[r - 1][1] = _map[r][1];
		}
		for (int c = 2; c <= _c; c++) {
			_map[_r][c - 1] = _map[_r][c];
		}
		for (int r = _r - 1; r >= r2; r--) {
			_map[r + 1][_c] = _map[r][_c];
		}
		for (int c = _c - 1; c >= 2; c--) {
			_map[r2][c + 1] = _map[r2][c];
		}
		_map[r2][2] = 0;
	}

	static int find() {
		int sum = 2;

		for (int i = 1; i <= _r; i++) {
			for (int j = 1; j <= _c; j++) {
				sum += _map[i][j];
			}
		}
		return sum;
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_r = Integer.parseInt(tokens.nextToken());
		_c = Integer.parseInt(tokens.nextToken());
		_t = Integer.parseInt(tokens.nextToken());

		_map = new int[_r + 1][_c + 1];
		_ac = new ArrayList<>();
		for (int i = 1; i <= _r; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 1; j <= _c; j++) {
				_map[i][j] = Integer.parseInt(tokens.nextToken());
				if (_map[i][j] == -1) {
					_ac.add(i);

				}
			}
		} // 입력

		for (int time = 0; time < _t; time++) {
			spread();
			wind();
		}

		System.out.println(find());

	}

}
