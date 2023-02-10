import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] deltas = { { 1, -1 }, { 1, 0 }, { 1, 1 } };

	static boolean isIn(int r, int c) {
		return 1 <= r && r < _n + 1 && 0 <= c && c < _m;
	}

	static int _n, _m; // 2 <= _n,_m <= 1000
	static int[][] _map;
	static int[][][] _memo;

	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());

		_map = new int[_n + 1][_m];
		for (int i = 1; i <= _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < _m; j++) {
				_map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}

		_memo = new int[_n + 1][_m][3]; // 0은 왼쪽아래, 1은 가운데아래, 2는 오른쪽아래

		for (int r = 0; r < _n + 1; r++) {
			_memo[r][0][0] = 1000000000;
			_memo[r][_m - 1][2] = 1000000000;
		}

		for (int r = 0; r < _n; r++) {
			for (int c = 0; c < _m; c++) {
				// 다음 줄
				for (int d = 0; d < 3; d++) {
					int nr = r + deltas[d][0];
					int nc = c + deltas[d][1];
					if (isIn(nr, nc)) {
						if (d == 0) {
							_memo[nr][nc][2] = Math.min(_memo[r][c][0] + _map[nr][nc], _memo[r][c][1] + _map[nr][nc]);
						} else if (d == 1) {
							_memo[nr][nc][1] = Math.min(_memo[r][c][0] + _map[nr][nc], _memo[r][c][2] + _map[nr][nc]);
						} else {
							_memo[nr][nc][0] = Math.min(_memo[r][c][1] + _map[nr][nc], _memo[r][c][2] + _map[nr][nc]);
						}
					}
				}
			}
		}

//		for (int r = 0; r < _n + 1; r++) {
//			for (int c = 0; c < _m; c++) {
//				for (int i = 0; i < 3; i++) {
//					System.out.print(_memo[r][c][i] + " ");
//				}
//				System.out.print("///");
//			}
//			System.out.println();
//		}

		int answer = Integer.MAX_VALUE;
		for (int c = 0; c < _m; c++) {
			for (int i = 0; i < 3; i++) {
//				System.out.println(_memo[_n][c][i]);
				answer = Math.min(answer, _memo[_n][c][i]);
			}
		}

		System.out.println(answer);
	}

}