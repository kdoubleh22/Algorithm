package bj.b5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B5_02475 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());

		int answer = 0;
		for (int i = 0; i < 5; i++) {
			int num = Integer.parseInt(tokens.nextToken());
			answer += Math.pow(num, 2);
		}

		answer %= 10;

		System.out.println(answer);
	}

}
