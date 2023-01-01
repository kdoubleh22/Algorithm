package bj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G3_20057 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] _deltas = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } }; // 왼 아래 오 위

	static double[] _amounts = { 0.01, 0.01, 0.02, 0.02, 0.05, 0.07, 0.07, 0.1, 0.1 };

	static int[][][] _spread = {
			{ { -1, 1 }, { 1, 1 }, { -2, 0 }, { 2, 0 }, { 0, -2 }, { -1, 0 }, { 1, 0 }, { -1, -1 }, { 1, -1 } },
			{ { -1, -1 }, { -1, 1 }, { 0, -2 }, { 0, 2 }, { 2, 0 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 1 } },
			{ { -1, -1 }, { 1, -1 }, { -2, 0 }, { 2, 0 }, { 0, 2 }, { -1, 0 }, { 1, 0 }, { -1, 1 }, { 1, 1 } },
			{ { 1, -1 }, { 1, 1 }, { 0, -2 }, { 0, 2 }, { -2, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 1 } } };

	static int _n, _answer, _x, _y, _dir;
	static int[][] _map;

	static boolean isIn(int x, int y) {
		return 0 <= x && x < _n && 0 <= y && y < _n;
	}

	static void spreadSand() {
		int sum = _map[_x][_y];
		for (int i = 0; i < 9; i++) {
			int nx = _x + _spread[_dir][i][0];
			int ny = _y + _spread[_dir][i][1];

			// 모래 양 계산
			int amount = (int) (_map[_x][_y] * _amounts[i]);
			// 범위 안에 있으면 _map에 +하고
			if (isIn(nx, ny)) {
				_map[nx][ny] += amount;
			} else {
				// 범위 밖이면 정답에 +.
				_answer += amount;
			}
			// sum에는 -해줌.
			sum -= amount;
		}

		// a 옮기기.
		int nx = _x + _deltas[_dir][0];
		int ny = _y + _deltas[_dir][1];
		if (isIn(nx, ny)) {
			_map[nx][ny] += sum;
		} else {
			// 범위 밖이면 정답에 +.
			_answer += sum;
		}

		// y위치 비워주기
		_map[_x][_y] = 0;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_map = new int[_n][_n];

		for (int i = 0; i < _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < _n; j++) {
				_map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}

		// 초기화
		_answer = 0;
		_x = _n / 2;
		_y = _n / 2;
		int step = 1;
		int stepCnt = 0;
		_dir = 0;

		// 왼쪽-아래-오른쪽-위 규칙적인 이동
		outer: while (true) {
			for (int i = 0; i < step; i++) {
				// 이동
				_x = _x + _deltas[_dir][0];
				_y = _y + _deltas[_dir][1];
				// 모래 날리기
				spreadSand();
				// 종료 조건
				if (_x == 0 && _y == 0) {
					break outer;
				}
			}

			// 방향 바꿔주기
			_dir = (_dir + 1) % 4;

			// 변수 업데이트
			if (++stepCnt == 2) {
				// step 1늘어나고
				step++;
				// step count 초기화.
				stepCnt = 0;
			}
		}

		System.out.println(_answer);

	}

}
