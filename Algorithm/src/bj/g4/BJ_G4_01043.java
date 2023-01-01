package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ_G4_01043 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m;
	static Set<Integer> _truths;
	static int[][] _parties;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());

		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());

		tokens = new StringTokenizer(input.readLine());
		int num = Integer.parseInt(tokens.nextToken());

		_truths = new HashSet<>();
		for (int i = 0; i < num; i++) {
			_truths.add(Integer.parseInt(tokens.nextToken()));
		}

		_parties = new int[_m][];
		for (int i = 0; i < _m; i++) {
			tokens = new StringTokenizer(input.readLine());
			int cnt = Integer.parseInt(tokens.nextToken());
			_parties[i] = new int[cnt + 1];
			for (int j = 1; j <= cnt; j++) {
				_parties[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}

		int answer = _m;
		while (true) {
			boolean isDone = true;
			for (int i = 0; i < _m; i++) {
				if (_parties[i][0] == -1) {
					continue;
				} else {
					j: for (int j = 1; j < _parties[i].length; j++) {
						if (_truths.contains(_parties[i][j])) { // 거짓말을 하면 안 되는 사람이면,
							answer--;
							for (int k = 1; k < _parties[i].length; k++) {
								_truths.add(_parties[i][k]);
							}
							_parties[i][0] = -1;
							isDone = false;
							break j;
						}
					}
				}
			}
			if (isDone) {
				break;
			}

		}
		System.out.println(answer);
	}

}
