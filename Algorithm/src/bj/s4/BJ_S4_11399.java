package bj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_S4_11399 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static int[] _arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_arr = new int[_n];

		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < _n; i++) {
			_arr[i] = Integer.parseInt(tokens.nextToken());
		}

		// 정렬하고,
		Arrays.sort(_arr);

		// 맨앞자리는 _n번, 쭉쭉쭉 맨 끝은 1번 더하면 됨.
		int answer = 0;
		for (int i = 0; i < _n; i++) {
			answer += _arr[i] * (_n - i);
		}

		System.out.println(answer);

	}

}
