import java.io.*;
import java.util.*;

/*
 * -풀이
 */

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static int[][] _map;
	static int[][] _max;
	static int[][] _min;

	static boolean isIn(int col) {
		return 0 <= col && col < 3;
	}

	public static void main(String[] args) throws Exception {
		_n = Integer.parseInt(input.readLine());

		_map = new int[_n + 1][3];

		// 입력
		for (int i = 1; i <= _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < 3; j++) {
				_map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}

		// 입력 확인.
//		for(int[] row:_map) {
//			System.out.println(Arrays.toString(row));
//		}

		_max = new int[_n + 1][3];
		_min = new int[_n + 1][3];

		for (int i = 1; i <= _n; i++) {
			_max[i][0] = _map[i][0] + Math.max(_max[i - 1][0], _max[i - 1][1]);
			_max[i][1] = _map[i][1] + Math.max(Math.max(_max[i - 1][0], _max[i - 1][1]), _max[i - 1][2]);
			_max[i][2] = _map[i][2] + Math.max(_max[i - 1][1], _max[i - 1][2]);

			_min[i][0] = _map[i][0] + Math.min(_min[i - 1][0], _min[i - 1][1]);
			_min[i][1] = _map[i][1] + Math.min(Math.min(_min[i - 1][0], _min[i - 1][1]), _min[i - 1][2]);
			_min[i][2] = _map[i][2] + Math.min(_min[i - 1][1], _min[i - 1][2]);
		}

		output.append(Math.max(Math.max(_max[_n][0], _max[_n][1]), _max[_n][2])).append(" ");
		output.append(Math.min(Math.min(_min[_n][0], _min[_n][1]), _min[_n][2]));

		System.out.println(output);

	}

}