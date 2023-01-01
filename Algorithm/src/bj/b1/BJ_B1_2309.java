package bj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_B1_2309 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[] _dwarfs;
	static int[] _results;
	static boolean _isFinded;

	static void comb(int cnt, int start, int sum) {
		if (cnt == 7) {
			if (sum == 100) {
				Arrays.sort(_results);
				for (int r : _results) {
					output.append(r).append("\n");
				}
				_isFinded = true;
			}
			return;
		}

		for (int i = start; i < 9; i++) {
			_results[cnt] = _dwarfs[i];
			comb(cnt + 1, i + 1, sum + _dwarfs[i]);
			if (_isFinded) {
				return;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_results = new int[7];
		_dwarfs = new int[9];
		for (int i = 0; i < 9; i++) {
			_dwarfs[i] = Integer.parseInt(input.readLine());
		}

		_isFinded = false;
		comb(0, 0, 0);

		System.out.println(output);
	}

}
