package bj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author me
 * @see https://www.acmicpc.net/problem/11659
 * @performance
 * @category @memo prefix sum 알고리즘. O(n)에 미리 합을 구해놓고 O(1)으로 합을 구함. 주의: a~b까지의
 *           합이면 sum(b)-sum(a-1). output에 append할 때, string.format 시간이 오래 걸리는 듯.
 *           그냥 append만 사용하니까 통과됐다. https://boycoding.tistory.com/224 참고.
 * 
 */

public class BJ_S3_11659 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		int n = Integer.parseInt(tokens.nextToken());
		int m = Integer.parseInt(tokens.nextToken());
		int[] nums = new int[n];
		int[] sum = new int[n + 1];

		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(tokens.nextToken());
		}

		// 구간합 알고리즘
		sum[0] = 0;
		for (int i = 1; i < n + 1; i++) {
			sum[i] = sum[i - 1] + nums[i - 1];
		}
//		for (int i = 0; i < n; i++) {
//			System.out.printf("%d ", sum[i]);
//		}
		long beforeTime = System.currentTimeMillis();
		for (int k = 0; k < m; k++) {
			tokens = new StringTokenizer(input.readLine());
			int i = Integer.parseInt(tokens.nextToken());
			int j = Integer.parseInt(tokens.nextToken());
//			output.append(String.format("%d%n", sum[j]-sum[i-1]));
			output.append(sum[j] - sum[i - 1]).append("\n");
		}
		long afterTime = System.currentTimeMillis();
		System.out.println(afterTime - beforeTime);
		System.out.print(output);
	}

}
