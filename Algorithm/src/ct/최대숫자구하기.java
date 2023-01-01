package ct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class 최대숫자구하기 {

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
		for (int i = 1; i <= _m; i++) {
			_ts.add(i);
		}

		tokens = new StringTokenizer(input.readLine());
		while (_n-- > 0) {
			int num = Integer.parseInt(tokens.nextToken());
			_ts.remove(num);
			output.append(_ts.last()).append("\n");
		}

		System.out.println(output);

	}

}
