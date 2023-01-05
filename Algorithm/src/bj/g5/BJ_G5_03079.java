package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G5_03079 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m;
	static int[] _times;

	static long cal(long time) {
		long result = 0;
		for (int i = 0; i < _n; i++) {
			result += time / _times[i];

			// 오버플로우 처리
			if (result < 0) {
				return _m;
			}
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());

		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());

		_times = new int[_n];
		for (int i = 0; i < _n; i++) {
			_times[i] = Integer.parseInt(input.readLine());
		}

		long impossible = 0;
		long possible = (long) Math.pow(10, 18);

		while (impossible < possible) {
			if (possible == impossible + 1) {
				break;
			}
			long cur = (impossible + possible) / 2;
//			System.out.println("possible:" + possible + "impossible:" + impossible + "cur:" + cur);
			long result = cal(cur);
			if (result >= _m) {
				possible = cur;
			} else if (result < _m) {
				impossible = cur;
			}
		}

		System.out.println(possible);
	}

}
