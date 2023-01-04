package bj.g5;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ_G5_02470 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static int[] _solutions;
	static int _sum;
	static int _big, _small;
	static Set<Point> _done;

	static void recur(int start, int end) {
		// 방문처리
		_done.add(new Point(start, end));

		if (start == end) {
			return;
		}

		if (Math.abs(_solutions[start] + _solutions[end]) <= _sum) {
			_sum = Math.abs(_solutions[start] + _solutions[end]);
			_big = _solutions[end];
			_small = _solutions[start];
		}

		if (Math.abs(_solutions[start + 1] + _solutions[end]) < Math.abs(_solutions[start] + _solutions[end - 1])) {
			if (!_done.contains(new Point(start + 1, end))) {
				recur(start + 1, end);
			}
		} else if (Math.abs(_solutions[start + 1] + _solutions[end]) > Math
				.abs(_solutions[start] + _solutions[end - 1])) {
			if (!_done.contains(new Point(start, end - 1))) {
				recur(start, end - 1);
			}
		} else {
			if (!_done.contains(new Point(start + 1, end))) {
				recur(start + 1, end);
			}
			if (!_done.contains(new Point(start, end - 1))) {
				recur(start, end - 1);
			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_solutions = new int[_n];
		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < _n; i++) {
			_solutions[i] = Integer.parseInt(tokens.nextToken());
		}

		Arrays.sort(_solutions); // 오름차순
		_sum = Math.abs(_solutions[0] + _solutions[_n - 1]);
		;
		_done = new HashSet<>();

		if (!_done.contains(new Point(0, _n - 1))) {
			recur(0, _n - 1);
		}

		output.append(_small).append(" ").append(_big);

		System.out.println(output);

	}

}
