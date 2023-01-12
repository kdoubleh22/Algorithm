package bj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * -문제
 * LCS
 * -풀이
 * DP+이진탐색
 */
public class BJ_S2_18353 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static int[] _power;
	static int[] _last;
	static int _length;

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_power = new int[_n];

		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < _n; i++) {
			_power[i] = Integer.parseInt(tokens.nextToken());
		}

		_last = new int[_n];
		Arrays.fill(_last, -1);

		_length = 0;
		for (int i = 0; i < _n; i++) {
			System.out.println(_power[i]);

			for (int j = 0; j < _n; j++) {
				System.out.print(_last[j] + " ");
			}
			System.out.println();

			int location = Arrays.binarySearch(_last, _power[i]);

//			System.out.println(location);
			// 이미 있을 때,
			if (location > 0) {
				continue;
			}
			// 없을 때,
			else {
				// 들어가야할 위치
				location = (-1 * location) - 1;
//				System.out.println(locationF);
				// 첫번째가 _n-1.
				if ((_n + 1 - location) >= _length) {
					_length = _n + 1 - location;
					if (_last[location] <= _power[i]) {
						System.out.println("in");
						_last[location] = _power[i];
					}
				}
			}
		}

		System.out.println(_n - _length);

	}

}
