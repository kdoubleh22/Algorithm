package swea.nd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author hh
 * @memo V2는 순열을 다 돌고, 따로 함수에서 sum으로 가지치기. 난이도는 이게 쉬움. 4^12로 풀었을 땐 제한시간 초과. 1년은
 *       한 번이면 되니, 3으로 푼다면? 3으로 하면 아래같은 성능. 정말 많이 차이난다. 웬만하면 순조부 구할 때 가지치기 하는게
 *       좋겠다.
 * @performance 4로 풀면 제한시간 초과. 3으로 풀면 105,120 kb 1,504 ms.
 */

public class SWEA_ND_01952_V2 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _t, _answer;
	static int[] _fees, _plans; // 0: 1일, 1: 1달, 2: 3달, 3: 1년

	static int findSum(int[] choosed) {
		int sum = 0;

		int[] planCopy = Arrays.copyOf(_plans, _plans.length);

		for (int i = 0; i < 12; i++) {
			int type = choosed[i];
			if (planCopy[i] != 0) {
				if (type == 0) { // 1일
					sum += _fees[0] * planCopy[i];
				} else if (type == 1) { // 1달
					sum += _fees[1];
				} else { // 3달
					sum += _fees[2];
					for (int m = i; m < i + 3 && m < 12; m++) {
						planCopy[m] = 0;
					}
				}
			}
			if (sum >= _answer) {
				return sum;
			}
		}

		return sum;
	}

	static void dupPerm(int cnt, int[] choosed) {

		if (cnt == 12) {
			_answer = Math.min(_answer, findSum(choosed));
			return;
		}

		for (int i = 0; i < 3; i++) {
			choosed[cnt] = i;
			dupPerm(cnt + 1, choosed);
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
			dupPerm(0, new int[12]);
			_answer = Math.min(_answer, _fees[3]);

			output.append("#").append(tc).append(" ").append(_answer).append("\n");

		} // tc

		System.out.println(output);

	}

}
