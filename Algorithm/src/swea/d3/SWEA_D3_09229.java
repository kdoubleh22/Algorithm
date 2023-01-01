package swea.d3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author me
 * @see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AW8Wj7cqbY0DFAXN
 * @performance 26,012 kb 156 ms
 * @category #조합
 * @memo 조합은 cnt, start, 다음index부터.
 * 
 */

public class SWEA_D3_09229 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int n, m, max;
	static int[] weights;

	static void comb(int cnt, int start, int sum) {
		if (sum > m) {
			return;
		}

		if (cnt == 2) {
			if (sum > max) {
				max = sum;
			}
			return;
		}

		for (int i = start; i < n; i++) {
			comb(cnt + 1, i + 1, sum + weights[i]);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
//		input = new BufferedReader(new FileReader("./swea9229input.txt"));//d

		int TC = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			tokens = new StringTokenizer(input.readLine());
			n = Integer.parseInt(tokens.nextToken());
			m = Integer.parseInt(tokens.nextToken());

			weights = new int[n];
			max = Integer.MIN_VALUE;

			tokens = new StringTokenizer(input.readLine());
			for (int i = 0; i < n; i++) {
				weights[i] = Integer.parseInt(tokens.nextToken());
			}

			comb(0, 0, 0);

			if (max == Integer.MIN_VALUE) {
				max = -1;
			}

			output.append("#").append(tc).append(" ").append(max).append("\n");

		}

		System.out.print(output);

	}

}
