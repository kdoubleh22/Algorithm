package ct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class 정수명령어 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _t, _k;
	static TreeSet<Integer> _ts;

	public static void main(String[] args) throws NumberFormatException, IOException {
		_t = Integer.parseInt(input.readLine());
		while (_t-- > 0) {
			_k = Integer.parseInt(input.readLine());

			_ts = new TreeSet<>();
			while (_k-- > 0) {
				tokens = new StringTokenizer(input.readLine());
				String i1 = tokens.nextToken();
				int i2 = Integer.parseInt(tokens.nextToken());
				op(i1, i2);
			}
			
			if (_ts.isEmpty()) {
				output.append("EMPTY").append("\n");
			} else {
				output.append(_ts.last()).append(" ").append(_ts.first()).append("\n");
			}
		}

		System.out.println(output);

	}

	private static void op(String i1, int i2) {
		if (i1.equals("I")) {
			_ts.add(i2);
		} else {
			if (!_ts.isEmpty()) {
				if (i2 == 1) {
					_ts.remove(_ts.last());
				} else {
					_ts.remove(_ts.first());
				}
			}
		}
	}

}
