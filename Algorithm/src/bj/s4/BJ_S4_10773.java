package bj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S4_10773 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _k;
	static int[] _nums;

	public static void main(String[] args) throws NumberFormatException, IOException {
		_k = Integer.parseInt(input.readLine());

		_nums = new int[_k + 1];

		int cnt = 0;
		for (int i = 0; i < _k; i++) {
			int num = Integer.parseInt(input.readLine());
			if (num == 0) {
				cnt--;
			} else {
				_nums[++cnt] = num;
			}
		}

		int sum = 0;
		for (int i = 1; i <= cnt; i++) {
			sum += _nums[i];
		}

		System.out.println(sum);

	}

}
