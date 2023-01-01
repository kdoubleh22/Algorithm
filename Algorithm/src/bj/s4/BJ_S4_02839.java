package bj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S4_02839 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());
		int five = _n / 5;
		_answer = -1;
		for (int i = five; i >= 0; i--) {
			if ((_n - 5 * i) % 3 == 0) {
				_answer = i + (_n - 5 * i) / 3;
				break;
			}
		}
		output.append(_answer);
		System.out.print(output);
	}

}
