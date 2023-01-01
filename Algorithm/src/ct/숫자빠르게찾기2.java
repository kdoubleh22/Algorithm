package ct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class 숫자빠르게찾기2 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m;
	static TreeSet<Integer> _ts;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());

		_ts = new TreeSet<>();
		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < _n; i++) {
			_ts.add(Integer.parseInt(tokens.nextToken()));
		}

		for (int i = 0; i < _m; i++) {
			int num = Integer.parseInt(input.readLine());
			Integer c = _ts.ceiling(num);
			if (c == null) {
				output.append(-1).append("\n");
			} else {
				output.append(c).append("\n");
			}
		}

		System.out.println(output);

	}

}
