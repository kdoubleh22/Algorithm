package swea.d4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D4_07465 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m;
	static int[] _reps;

	static boolean union(int x, int y) {
		int repX = find(x);
		int repY = find(y);
		if (repX == repY) {
			return false;
		} else {
			_reps[repY] = repX;
			return true;
		}

	}

	static int find(int x) {
		if (_reps[x] == x) {
			return x;
		} else {
			return _reps[x] = find(_reps[x]);
		}
	}

	static void make() {
		_reps = new int[_n + 1];
		for (int i = 1; i <= _n; i++) {
			_reps[i] = i;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
//		input = new BufferedReader(new FileReader("./io/swea07465input.txt"));

		int t = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= t; tc++) {
			tokens = new StringTokenizer(input.readLine());
			_n = Integer.parseInt(tokens.nextToken());
			_m = Integer.parseInt(tokens.nextToken());

			make();

			int answer = _n;
			for (int i = 0; i < _m; i++) {
				tokens = new StringTokenizer(input.readLine());
				int a = Integer.parseInt(tokens.nextToken());
				int b = Integer.parseInt(tokens.nextToken());
				if (union(a, b)) {
					answer--;
				}
			}

			output.append("#").append(tc).append(" ").append(answer).append("\n");

		}

		System.out.println(output);

	}

}
