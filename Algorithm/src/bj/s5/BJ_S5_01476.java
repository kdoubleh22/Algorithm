package bj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S5_01476 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _e, _s, _m;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_e = Integer.parseInt(tokens.nextToken());
		_s = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());

		int answer = 1;
		int e = 1;
		int s = 1;
		int m = 1;
		while (true) {
			if (e == _e && s == _s && m == _m) {
				break;
			}
			e++;
			s++;
			m++;
			if (e > 15) {
				e = 1;
			}
			if (s > 28) {
				s = 1;
			}
			if (m > 19) {
				m = 1;
			}
			answer++;
		}

		System.out.println(answer);
	}

}
