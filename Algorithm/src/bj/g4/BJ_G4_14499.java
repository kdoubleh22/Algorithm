package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G4_14499 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] deltas = { {}, { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

	static int _n, _m, _x, _y, _k;
	static int[][] _map;
	static int[] _dial, _status, _tempStatus;

	static boolean isIn(int r, int c) {
		return 0 <= r && r < _n && 0 <= c && c < _m;
	}

	static void move(int dir) {
//		System.out.println("_x:" + _x + " _y:" + _y);
//		System.out.println("dir" + dir);
		int nx = _x + deltas[dir][0];
		int ny = _y + deltas[dir][1];

		if (isIn(nx, ny)) {
			// 주사위 굴리기 처리.
			// 위치 업데이트.
			_x = nx;
			_y = ny;
			for (int i = 0; i < 6; i++) {
				_tempStatus[i] = _status[i];
			}
			// 0:위 1:동 2:서 3:북 4:남 5:아래
			if (dir == 1) { // 동
				_status[0] = _tempStatus[2];
				_status[1] = _tempStatus[0];
				_status[2] = _tempStatus[5];
				_status[3] = _tempStatus[3];
				_status[4] = _tempStatus[4];
				_status[5] = _tempStatus[1];
			} else if (dir == 2) { // 서
				_status[0] = _tempStatus[1];
				_status[1] = _tempStatus[5];
				_status[2] = _tempStatus[0];
				_status[3] = _tempStatus[3];
				_status[4] = _tempStatus[4];
				_status[5] = _tempStatus[2];
			} else if (dir == 3) { // 북
				_status[0] = _tempStatus[4];
				_status[1] = _tempStatus[1];
				_status[2] = _tempStatus[2];
				_status[3] = _tempStatus[0];
				_status[4] = _tempStatus[5];
				_status[5] = _tempStatus[3];
			} else if (dir == 4) { // 남
				_status[0] = _tempStatus[3];
				_status[1] = _tempStatus[1];
				_status[2] = _tempStatus[2];
				_status[3] = _tempStatus[5];
				_status[4] = _tempStatus[0];
				_status[5] = _tempStatus[4];
			}

			// map이 0이면, 주사위 바닥면을 map에 복사.
			if (_map[nx][ny] == 0) {
				_map[nx][ny] = _dial[_status[5]];
			}
			// map이 0이 아니면, map에 있는 수를 주사위에 복사.
			else {
				_dial[_status[5]] = _map[nx][ny];
				_map[nx][ny] = 0;
			}

			// 출력
			output.append(_dial[_status[0]]).append("\n");
		} else { // 바깥으로 이동하려는 경우에는 무시.
			return;
		}
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());
		_x = Integer.parseInt(tokens.nextToken());
		_y = Integer.parseInt(tokens.nextToken());
		_k = Integer.parseInt(tokens.nextToken());

		_map = new int[_n][_m];
		for (int i = 0; i < _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < _m; j++) {
				_map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}

		tokens = new StringTokenizer(input.readLine());
		_dial = new int[7];
		_status = new int[] { 1, 3, 4, 2, 5, 6 }; // 차례대로, 0:위 1:동 2:서 3:북 4:남 5:아래.
		_tempStatus = new int[6];
		while (_k-- > 0) {
			move(Integer.parseInt(tokens.nextToken()));

//			for (int[] row : _map) {
//				System.out.println(Arrays.toString(row));
//			}
//			System.out.println("================");
		}

		System.out.println(output);

	}

}
