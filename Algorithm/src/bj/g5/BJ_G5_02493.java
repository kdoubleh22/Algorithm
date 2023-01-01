package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author me
 * @see https://www.acmicpc.net/problem/2493
 * @performance 94656 KB 524 ms
 * @category #자료 구조 #스택
 * @memo 1<=N<=500,000. O(n*2)는 시간초과. 문제 꼼꼼히 읽기-'N개의 높이가 서로 다른 탑' 꼼꼼하게 모든 경우의 수
 *       생각하기, 생략해도 되는 조건이라도 명시하기?
 * 
 */

public class BJ_G5_02493 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int n;
	static int[] heights;

	public static void main(String[] args) throws NumberFormatException, IOException {
		n = Integer.parseInt(input.readLine());
		tokens = new StringTokenizer(input.readLine());

		heights = new int[n];

		for (int i = 0; i < n; i++) {
			heights[i] = Integer.parseInt(tokens.nextToken());
		}

		int[] receivers = new int[n]; // 수신탑들.

		int highestIdx = n - 1; // 현재 가장 높은 탑의 위치.

		// 내림차순이면 한꺼번에 update.
		for (int i = n - 1; i >= 1; i--) {
			if (heights[i - 1] < heights[i]) { // 왼쪽이 작으면,
				continue;
			} else { // 왼쪽이 크면,
				if (heights[i - 1] > heights[highestIdx]) { // highest보다 크면,
					// 사이에 있는 탑들 전부 업데이트.
					for (int j = i; j <= highestIdx; j++) {
						if (receivers[j] == 0) {
							receivers[j] = (i - 1) + 1;
						}
					}
					highestIdx = i - 1;
				} else { // highest보다 작으면
					// i보다 낮은 탑들 업데이트.
					int idx = i;
					while (heights[idx] < heights[i - 1]) { // 커지면 종료.
						if (receivers[idx] == 0) {
							receivers[idx] = (i - 1) + 1;
						}
						idx++;
					}
				}
			}
		}

		for (int i = 0; i < n; i++) {
			output.append(receivers[i]).append(" ");
		}

		System.out.print(output);
	}

}
