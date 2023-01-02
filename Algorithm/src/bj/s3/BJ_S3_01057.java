package bj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S3_01057 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _jm, _hs;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_jm = Integer.parseInt(tokens.nextToken());
		_hs = Integer.parseInt(tokens.nextToken());

		int round = 1;
		while (_n > 1) {
			if ((_jm % 2 == 1 && _jm + 1 == _hs) || (_hs % 2 == 1 && _hs + 1 == _jm)) {
				output.append(round);
				break;
			}

			_jm = (_jm + 1) / 2;
			_hs = (_hs + 1) / 2;
			_n = (_n + 1) / 2;
			round += 1;
		}

		if (_n <= 1) {
			output.append(-1);
		}

		System.out.println(output);
	}

}
