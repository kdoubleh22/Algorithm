package swea.nd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * 
 * @author hh
 * @time 20:52~22:05
 */

public class SWEA_ND_05658 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _k;
	static char[] _cNums;
	static int[] _nums;
	static TreeSet<Integer> _ts;

	static int cTOi(char c) {
		if (c >= '0' && c <= '9') {
			return c - '0';
		} else {
			return c - 'A' + 10;
		}
	}

	static void makeNum(int[] nums) {
		for (int i = 0; i < _n; i += _n / 4) {
			int made = 0;
			for (int j = (_n / 4) - 1; j >= 0; j--) {
				made += nums[i + (_n / 4) - 1 - j] * Math.pow(16, j);
			}
			_ts.add(made);
		}
	}

	static void rotate() {
		int temp = _nums[_n - 1];
		for (int i = _n - 2; i >= 0; i--) {
			_nums[i + 1] = _nums[i];
		}
		_nums[0] = temp;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
//		input = new BufferedReader(new FileReader("./io/swea05658input.txt"));

		int t = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= t; tc++) {
			tokens = new StringTokenizer(input.readLine());
			_n = Integer.parseInt(tokens.nextToken());
			_k = Integer.parseInt(tokens.nextToken());

			char[] _cNums = input.readLine().toCharArray();

			_nums = new int[_n];
			for (int i = 0; i < _n; i++) {
				_nums[i] = cTOi(_cNums[i]);
			} // 입력 끝

			_ts = new TreeSet<>();
			for (int i = 0; i < _n / 4; i++) {
				rotate();
				makeNum(_nums);
			}

			for (int i = 0; i < _k - 1; i++) {
				_ts.pollLast();
			}

			output.append("#").append(tc).append(" ").append(_ts.pollLast()).append("\n");

		}

		System.out.println(output);

	}

}
