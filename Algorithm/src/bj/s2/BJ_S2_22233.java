package bj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * -풀이
 * HashSet에 담아놓고, 있으면 삭제.
 */

public class BJ_S2_22233 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m;
	static Set<String> _hs;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());

		_hs = new HashSet<>();

		for (int i = 0; i < _n; i++) {
			_hs.add(input.readLine());
		}

		int memoCnt = _n;
		for (int i = 0; i < _m; i++) {
			tokens = new StringTokenizer(input.readLine(), ",");
			while (tokens.hasMoreTokens()) {
				String keyword = tokens.nextToken();
				if (_hs.contains(keyword)) {
					memoCnt -= 1;
					_hs.remove(keyword);
				}
			}

			output.append(memoCnt).append("\n");
		}

		System.out.println(output);
	}

}
