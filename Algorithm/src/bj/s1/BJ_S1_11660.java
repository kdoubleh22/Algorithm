package bj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author me
 * @see https://www.acmicpc.net/problem/11660
 * @performance 126164 KB 976 ms
 * @category #
 * @memo prefix sum 알고리즘. 구간설정이 어려우면 그리거나 출력해보기.
 * 
 */

public class BJ_S1_11660 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		int n = Integer.parseInt(tokens.nextToken());
		int m = Integer.parseInt(tokens.nextToken());
		int[][] table = new int[n][n];
		int[][] sum_row = new int[n + 1][n + 1];
		int[][] sum = new int[n + 1][n + 1];
		for (int i = 0; i < n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < n; j++) {
				table[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		// 구간합 구하기.
		for (int i = 1; i < n + 1; i++) {
//			sum_row[i][0]=0;
			for (int j = 1; j < n + 1; j++) {
				sum_row[i][j] = sum_row[i][j - 1] + table[i - 1][j - 1];
			}
		}
//		for(int i=0;i<n+1;i++) {
//			for(int j=0;j<n+1;j++) {
//				System.out.printf("%d ",sum_row[i][j]);
//			}
//			System.out.println();
//		}

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				sum[i][j] = sum[i - 1][j] + sum_row[i][j];
			}
		}
//		System.out.println();
//		for(int i=0;i<n+1;i++) {
//			for(int j=0;j<n+1;j++) {
//				System.out.printf("%d ",sum[i][j]);
//			}
//			System.out.println();
//		}

		for (int i = 0; i < m; i++) {
			tokens = new StringTokenizer(input.readLine());
			int x1 = Integer.parseInt(tokens.nextToken());
			int y1 = Integer.parseInt(tokens.nextToken());
			int x2 = Integer.parseInt(tokens.nextToken());
			int y2 = Integer.parseInt(tokens.nextToken());
			// 계산
//			System.out.println(sum[x2][y2]+" : "+sum[x2][y1-1]+" : "+sum[x1-1][y2]+" : "+sum[x1-1][y1-1]);
			output.append(sum[x2][y2] - sum[x2][y1 - 1] - sum[x1 - 1][y2] + sum[x1 - 1][y1 - 1]).append("\n");
		}
		System.out.print(output);

	}

}
