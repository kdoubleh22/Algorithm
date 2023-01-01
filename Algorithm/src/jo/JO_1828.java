package jo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class JO_1828 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static Chemical[] _chemicals;

	static class Chemical implements Comparable<Chemical> {
		int low, high;

		public Chemical(int low, int high) {
			this.low = low;
			this.high = high;
		}

		@Override
		public int compareTo(Chemical o) {
			return Integer.compare(this.high, o.high);
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_chemicals = new Chemical[_n];
		for (int i = 0; i < _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			_chemicals[i] = new Chemical(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
		} // 입력 끝.

		Arrays.sort(_chemicals);

		int answer = 1;
		int cHigh = _chemicals[0].high;
		for (int i = 1; i < _chemicals.length; i++) {
			if (_chemicals[i].low > cHigh) {
				answer++;
				cHigh = _chemicals[i].high;
			}
		}

		output.append(answer);
		System.out.print(output);

	}

}