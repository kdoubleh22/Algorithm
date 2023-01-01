package swea.nd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author hh
 * @memo V1은 순열을 다 돌지 않고, sum으로 미리 가지치리를 함. 3으로 풀었는데 실행시간이 증가한게 말이 되나?
 * @performance 4로 풀었을 때: 24,544 kb 151 ms 3으로 풀었을 때: 22,860 kb 182 ms
 */

public class SWEA_ND_01952_V1 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _t, _answer;
	static int[] _fees, _plans; // 0: 1일, 1: 1달, 2: 3달, 3: 1년

	static void dupPerm(int cnt, int sum, boolean[] calculated) {

		if (sum >= _answer) {
			return;
		}

		if (cnt == 12) {
			_answer = Math.min(_answer, sum);
			return;
		}

		for (int i = 0; i < 3; i++) {
			if (!calculated[cnt]) { // 계산된 적 없으면,
				if (i == 0) { // 1일
					calculated[cnt] = true;
					dupPerm(cnt + 1, sum + _fees[0] * _plans[cnt], calculated);
					calculated[cnt] = false;
				} else if (i == 1) { // 1달
					calculated[cnt] = true;
					dupPerm(cnt + 1, sum + _fees[1], calculated);
					calculated[cnt] = false;
				} else { // 3달
					for (int m = cnt; m < cnt + 3 && m < 12; m++) {
						calculated[m] = true;
					}
					dupPerm(cnt + 1, sum + _fees[2], calculated);
					for (int m = cnt; m < cnt + 3 && m < 12; m++) {
						calculated[m] = false;
					}
				}

			} else { // 계산된 적 있으면, 개수 증가시키고 건너 뜀.
				dupPerm(cnt + 1, sum, calculated);
			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
//		input = new BufferedReader(new FileReader("./io/swea01952input.txt"));

		_t = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= _t; tc++) {
			_fees = new int[4];
			tokens = new StringTokenizer(input.readLine());
			for (int i = 0; i < 4; i++) {
				_fees[i] = Integer.parseInt(tokens.nextToken());
			}

			_plans = new int[12];
			tokens = new StringTokenizer(input.readLine());
			for (int i = 0; i < 12; i++) {
				_plans[i] = Integer.parseInt(tokens.nextToken());
			} // 입력 끝.

			_answer = Integer.MAX_VALUE;
			dupPerm(0, 0, new boolean[12]);
			_answer = Math.min(_answer, _fees[3]);

			output.append("#").append(tc).append(" ").append(_answer).append("\n");

		} // tc

		System.out.println(output);

	}

}
