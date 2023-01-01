package swea.d4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D4_03289 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _t, _n, _m;
	static int[] _p;

	static boolean union(int x, int y) {
		int findX = find(x);
		int findY = find(y);
		if (findX == findY) {
			return false;
		} else {
			_p[findY] = findX;
			return true;
		}
	}

	static int find(int x) {
		if (_p[x] == x) {
			return x;
		} else {
			return _p[x] = find(_p[x]);
		}
	}

	static void make() {
		for (int i = 1; i <= _n; i++) {
			_p[i] = i;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		_t = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= _t; tc++) {
			output.append("#").append(tc).append(" ");
			tokens = new StringTokenizer(input.readLine());
			_n = Integer.parseInt(tokens.nextToken());
			_m = Integer.parseInt(tokens.nextToken());

			_p = new int[_n + 1];
			make();

			for (int i = 0; i < _m; i++) {
				tokens = new StringTokenizer(input.readLine());
				int op = Integer.parseInt(tokens.nextToken());
				int a = Integer.parseInt(tokens.nextToken());
				int b = Integer.parseInt(tokens.nextToken());
				if (op == 0) {
					union(a, b);
				} else {
					if (a == b) {
						output.append(1);
					} else {
						if (find(a) == find(b)) {
							output.append(1);
						} else {
							output.append(0);
						}
					}
				}
			}
			output.append("\n");
		}
		System.out.println(output);
	}

}
