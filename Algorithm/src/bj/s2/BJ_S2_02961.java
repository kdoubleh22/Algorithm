package bj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author doubleh
 * @see https://www.acmicpc.net/problem/1254
 * @performance 11524 KB 76 ms
 * @category #브루트포스 #비트마스킹 #부분집합
 * @memo 부분집합은 true rcursion false recursion. 공집합 체크하기. 선택되는 개수가 미확정, n제한이 10이다.
 *       공집합 제외
 * 
 */

public class BJ_S2_02961 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int n, answer;
	static int[][] ingredients;
	static boolean[] isSelected;

	static void recursion(int cnt) {
		if (cnt == n) {
			int mul = 1;
			int sum = 0;
			int falseCnt = 0;
			for (int i = 0; i < n; i++) {
				if (isSelected[i]) {
					mul *= ingredients[i][0];
					sum += ingredients[i][1];
				} else {
					falseCnt++;
				}
			}
			if (falseCnt == n) {
				return;
			}
			if (Math.abs(mul - sum) < answer) {
				answer = Math.abs(mul - sum);
			}
			return;
		}
		isSelected[cnt] = true;
		recursion(cnt + 1);
		isSelected[cnt] = false;
		recursion(cnt + 1);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		n = Integer.parseInt(input.readLine());

		ingredients = new int[n][2];

		for (int i = 0; i < n; i++) {
			tokens = new StringTokenizer(input.readLine());
			ingredients[i][0] = Integer.parseInt(tokens.nextToken());
			ingredients[i][1] = Integer.parseInt(tokens.nextToken());
		} // 입력 끝

		isSelected = new boolean[n];
		answer = Integer.MAX_VALUE;
		recursion(0);

		output.append(answer);
		System.out.print(output);

	}

}
