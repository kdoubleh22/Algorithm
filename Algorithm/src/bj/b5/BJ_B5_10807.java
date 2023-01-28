package bj.b5;

import java.io.*;
import java.util.*;

public class BJ_B5_10807 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static int _v;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		_n = Integer.parseInt(input.readLine());

		arr = new int[_n];
		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < _n; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
		}

		_v = Integer.parseInt(input.readLine());

		int cnt = 0;
		for (int i = 0; i < _n; i++) {
			if (arr[i] == _v) {
				cnt += 1;
			}
		}

		System.out.println(cnt);
	}

}