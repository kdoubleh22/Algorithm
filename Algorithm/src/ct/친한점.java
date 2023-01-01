package ct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class 친한점 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m;
	static TreeSet<Pair> _ts;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());

		_ts = new TreeSet<>();
		for (int i = 0; i < _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			_ts.add(new Pair(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
		}

		while (_m-- > 0) {
			tokens = new StringTokenizer(input.readLine());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());
			Pair c = _ts.ceiling(new Pair(x, y));
			if (c != null) {
				output.append(c.x).append(" ").append(c.y).append("\n");
			} else {
				output.append(-1).append(" ").append(-1).append("\n");
			}
		}

		System.out.println(output);

	}

	static class Pair implements Comparable<Pair> {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair o) {
			if (this.x == o.x) {
				return Integer.compare(this.y, o.y);
			} else {
				return Integer.compare(this.x, o.x);
			}
		}

	}

}
