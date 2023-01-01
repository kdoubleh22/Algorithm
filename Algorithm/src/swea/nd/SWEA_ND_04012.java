package swea.nd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author kdoubleh22
 * @see https://www.acmicpc.net/problem/2206
 * @performance 1. 기본: 29,156 kb 656 ms 2. 하나를 고정해놓으면: 26,908 kb 425 ms 3. 시너지
 *              합을 이중for문으로 구했을 때: 26,760 kb 164 ms
 * @category #
 * @memo 조합을 반만 구혀려면, 하나를 고정해놓고 가면 됨. 시간차이가 많이 나지 않으면, 그냥 편한걸로 풀어도 됨.
 * 
 */

public class SWEA_ND_04012 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int T, N, M, Min;
	static int[][] S;
	static int[] ChoosedA, ChoosedB;

	static boolean contain(int[] choosed, int num) {
		for (int i = 0; i < choosed.length; i++) {
			if (choosed[i] == num) {
				return true;
			}
		}
		return false;
	}

	static void calculate() {
		int sumA = 0;
		int sumB = 0;

		for (int i : ChoosedA) {
			for (int j : ChoosedA) {
				sumA += S[i][j];
			}
		}

		for (int i : ChoosedB) {
			for (int j : ChoosedB) {
				sumB += S[i][j];
			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				if (contain(ChoosedA, i) && contain(ChoosedA, j)) {
//					sumA += S[i][j];
//				} else if (contain(ChoosedB, i) && contain(ChoosedB, j)) {
//					sumB += S[i][j];
//				}
//			}
//		}
		int diff = Math.abs(sumA - sumB);
		if (diff < Min) {
			Min = diff;
		}
	}

	static void comb(int cnt, int start) {
		if (cnt == M) {
			boolean[] check = new boolean[N];
			for (int num : ChoosedA) {
				check[num] = true;
			}
			int idx = 0;
			for (int i = 0; i < N; i++) {
				if (!check[i]) {
					ChoosedB[idx++] = i;
				}
			}
			calculate();
			return;
		}

		for (int i = start; i < N; i++) {
			ChoosedA[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
//		input = new BufferedReader(new FileReader("./swea04012input.txt"));

		T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(input.readLine());

			S = new int[N][N];
			for (int i = 0; i < N; i++) {
				tokens = new StringTokenizer(input.readLine());
				for (int j = 0; j < N; j++) {
					S[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}

			M = N / 2;
			ChoosedA = new int[M];
			ChoosedB = new int[M];
			Min = Integer.MAX_VALUE;
			ChoosedA[0] = 0;
			comb(1, 1);

			output.append("#").append(tc).append(" ").append(Min).append("\n");
		}
		System.out.print(output);

	}

}
