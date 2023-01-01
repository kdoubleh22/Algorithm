package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author kdoubleh22
 * @see https://www.acmicpc.net/problem/2206
 * @performance 58004 KB 284 ms
 * @category #구현
 * @memo 디버깅이 도저히 안 될땐, 변수이름이 잘못됐는지 확인해보기. 여기서는 _map과 temp 헷갈림. 한시간정도는 그냥 날아감.
 *       음수 나머지 연산자는 일단 절대값으로 나머지 연산을 하고 왼쪽 부호를 따라감? cctv가 볼 수있는 방향을 배열에 넣어서
 *       정규화하면 훨씬 편함. 갈때까지 가보는건 dfs 스타일. 상황에 따라 temp지도를 사용할지, 원래 _map을 사용하고
 *       원상복구해놓을지 정해야됨. class 디버깅하기 편하게 toString 사용해보기.
 * 
 */

public class BJ_G4_15683 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m, _answer;
	static int[][] _map;
	static List<CCTV> _cctv;

	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static class CCTV {
		int type, r, c, direction;

		public CCTV(int type, int r, int c, int difrection) {
			this.type = type;
			this.r = r;
			this.c = c;
			this.direction = difrection; // 방향은 0:위 1:오른쪽 2:아래 3:왼쪽
		}

	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < _n && c >= 0 && c < _m;
	}

	static void detect(int[][] temp, int[] directions, int r, int c) {
		for (int i = 0; i < directions.length; i++) {
			int tempR = r; // 백업.
			int tempC = c;
			while (true) {
				int nr = tempR + deltas[directions[i]][0];
				int nc = tempC + deltas[directions[i]][1];
				if (isIn(nr, nc)) {
					if (_map[nr][nc] == 0) {
						temp[nr][nc] = -1;
						tempR = nr;
						tempC = nc;
						continue;
					} else if (_map[nr][nc] == 6) {
						break;
					} else { // cctv이거나, 탐색한 지역일 때, pass.
						tempR = nr;
						tempC = nc;
						continue;
					}
				} else {
					break;
				}
			}
		}
	}

	static void findBlindSpot() {
		int[][] temp = new int[_n][];
		// map 복사.
		for (int i = 0; i < _n; i++) {
			temp[i] = Arrays.copyOf(_map[i], _map[0].length);
		}

		// temp update.
		for (int i = 0; i < _cctv.size(); i++) {
			CCTV cctv = _cctv.get(i);
			int r = cctv.r;
			int c = cctv.c;
			if (cctv.type == 1) {
				int[] directions = { cctv.direction };
				detect(temp, directions, r, c);
			} else if (cctv.type == 2) {
				int[] directions = { cctv.direction, cctv.direction + 2 };
				detect(temp, directions, r, c);
			} else if (cctv.type == 3) {
				int[] directions = { cctv.direction, (cctv.direction + 1) % 4 };
				detect(temp, directions, r, c);
			} else if (cctv.type == 4) {
				int[] directions = { cctv.direction,
						(cctv.direction - 1 >= 0) ? cctv.direction - 1 : cctv.direction - 1 + 4,
						(cctv.direction + 1) % 4 };
				detect(temp, directions, r, c);
			} else if (cctv.type == 5) {
				int[] directions = { 0, 1, 2, 3 };
				detect(temp, directions, r, c);
			}
		}

		// 사각지대 계산
		int blindSpot = 0;
		for (int i = 0; i < _n; i++) {
			for (int j = 0; j < _m; j++) {
				if (temp[i][j] == 0) {
					blindSpot++;
				}
			}
		}

		if (blindSpot < _answer) {
			_answer = blindSpot;
		}
	}

	static void setCCTVDirection(int cnt, int[] directions) {
		if (cnt == _cctv.size()) {
			// 방향 세팅.
			for (int i = 0; i < _cctv.size(); i++) {
				_cctv.get(i).direction = directions[i];
			}
			// 사각지대 구하기.
			findBlindSpot();
			return;
		}

		if (_cctv.get(cnt).type == 1 || _cctv.get(cnt).type == 3 || _cctv.get(cnt).type == 4) {
			directions[cnt] = 0;
			setCCTVDirection(cnt + 1, directions);
			directions[cnt] = 1;
			setCCTVDirection(cnt + 1, directions);
			directions[cnt] = 2;
			setCCTVDirection(cnt + 1, directions);
			directions[cnt] = 3;
			setCCTVDirection(cnt + 1, directions);
		} else if (_cctv.get(cnt).type == 2) { // 2번 cctv는 0,1만 사용.
			directions[cnt] = 0;
			setCCTVDirection(cnt + 1, directions);
			directions[cnt] = 1;
			setCCTVDirection(cnt + 1, directions);
		} else { // 5번
			setCCTVDirection(cnt + 1, directions);
		}

	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());

		_map = new int[_n][_m];
		_cctv = new ArrayList<>();
		for (int i = 0; i < _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < _m; j++) {
				_map[i][j] = Integer.parseInt(tokens.nextToken());
				if (_map[i][j] >= 1 && _map[i][j] <= 5) {
					_cctv.add(new CCTV(_map[i][j], i, j, 0)); // 초기 방향설정은 0으로, 근데 의미는 없음.
				}
			}
		} // 입력 끝.

		_answer = Integer.MAX_VALUE;
		setCCTVDirection(0, new int[_cctv.size()]);

		output.append(_answer);
		System.out.print(output);
	}

}
