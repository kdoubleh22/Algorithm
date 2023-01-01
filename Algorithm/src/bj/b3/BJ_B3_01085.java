package bj.b3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B3_01085 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _x, _y, _w, _h;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_x = Integer.parseInt(tokens.nextToken());
		_y = Integer.parseInt(tokens.nextToken());
		_w = Integer.parseInt(tokens.nextToken());
		_h = Integer.parseInt(tokens.nextToken());

		int answer = Math.min(_x, _y);
		answer = Math.min(answer, _w - _x);
		answer = Math.min(answer, _h - _y);

		System.out.println(answer);

	}

}
