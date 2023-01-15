package bj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * -풀이
 * 
 */

public class BJ_S2_17245 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static int[] _heights;
	static long _total;

	public static void main(String[] args) throws IOException {
		_n = Integer.parseInt(input.readLine());

		_heights = new int[_n * _n]; // 각 칸에 몇 대씩 쌓여있는지.
		_total = 0; // 컴퓨터 전체 개수.
		int idx = 0;
		for (int i = 0; i < _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < _n; j++) {
				int computers = Integer.parseInt(tokens.nextToken());
				_heights[idx++] = computers;
				_total += computers;
			}
		}

		int totalCnt = _n * _n; // 칸의 개수.

		Arrays.sort(_heights);

//		System.out.println(Arrays.toString(_heights));

		long on = 0; // 켜진 컴퓨터의 개수.
		long half = (_total + 1) / 2; // 전체 컴퓨터 절반의 개수.
		int higherIdx = 0; // 현재 고려한 층보다 높은 층에 있는 컴퓨터의 칸 수.

		// 0대씩 쌓여있는건 고려하지 않음.
		while (_heights[higherIdx] == 0) {
			higherIdx += 1;
			if (higherIdx == totalCnt) { // 전부 0이라는 소리.
				break;
			}
		}

//		System.out.println("half:" + half);
		int answer = -1;

		if (higherIdx == totalCnt) { // 전부 0일 때는 답이 0.
			answer = 0;
		} else {
			for (int t = 1; t < 10000001; t++) {
//				System.out.println("higherIdx:" + higherIdx);

				on += totalCnt - higherIdx;

				int curHeight = _heights[higherIdx];

				while (_heights[higherIdx] <= curHeight) {
					higherIdx += 1;
					if (higherIdx == totalCnt) {
						higherIdx -= 1;
						break;
					}

				}

				System.out.println("time:" + t + " on:" + on);

				// 종료조건
				if (on >= half) {
					answer = t;
					break;
				}

			}
		}

		System.out.println(answer);
	}
}
