package bj.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * -풀이
 * 브루트포스는 대략 10만*10만/2 = 50억 -> 시간초과
 * 누적합, 부분합.
 */

public class BJ_G4_01806 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static int _s;
	static int[] _sum;

	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_s = Integer.parseInt(tokens.nextToken());

		_sum = new int[_n + 1]; // _sum[i]는 입력(_arr)의 i-1번째 인덱스까지의 합. 맨 앞자리는 0으로 패딩.
		tokens = new StringTokenizer(input.readLine());
		for (int i = 1; i < _n + 1; i++) {
			_sum[i] = _sum[i - 1] + Integer.parseInt(tokens.nextToken());
		}

		// 입력 확인.
//		System.out.println(Arrays.toString(_sum));

		int answer = Integer.MAX_VALUE;

		int left = 0;
		int right = 1;

		while (right > left && right < _n + 1) {

			int partialSum = _sum[right] - _sum[left];

//			System.out.println("left:" + left + "right:" + right + " partialSum:" + partialSum);

			// 부분합이 S보다 크거나 같으면, 정답을 업데이트 해주고, left를 한 칸 옮김.
			if (partialSum >= _s) {
				answer = Math.min(answer, right - left);
				left += 1;
			} else {
				right += 1;
			}
		}

		if (answer == Integer.MAX_VALUE) {
			answer = 0;
		}

		System.out.println(answer);
	}

}
