package bj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author kdoubleh22
 * @see https://www.acmicpc.net/problem/16935
 * @performance 85132 KB	584 ms
 * @category #구현
 * @memo 입력받을 때 변수 초기화 잘 해주기.
 * 
 */

public class BJ_S1_16935 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int N, M, R;
	static int[][] A;
	static int[] Rs;

	static int[][] operate(int operateNum) {
		int[][] temp;
		if (operateNum == 1) {
			temp = new int[N][M];
			for (int r = N - 1; r >= 0; r--) {
				for (int c = 0; c < M; c++) {
					temp[r][c] = A[N - 1 - r][c];
				}
			}
		} else if (operateNum == 2) {
			temp = new int[N][M];
			for (int c = M - 1; c >= 0; c--) {
				for (int r = 0; r < N; r++) {
					temp[r][c] = A[r][M - 1 - c];
				}
			}
		} else if (operateNum == 3) {
			temp = new int[M][N];
			for (int c = N - 1; c >= 0; c--) {
				for (int r = 0; r < M; r++) {
					temp[r][c] = A[N - 1 - c][r];
				}
			}
			int temp2 = N;
			N = M;
			M = temp2;
		} else if (operateNum == 4) {
			temp = new int[M][N];
			for (int c = 0; c < N; c++) {
				for (int r = M - 1; r >= 0; r--) {
					temp[r][c] = A[c][M - 1 - r];
				}
			}
			int temp2 = N;
			N = M;
			M = temp2;
		} else if (operateNum == 5) {
			temp = new int[N][M];
			int[][] temp1 = new int[N / 2][M / 2];
			int[][] temp2 = new int[N / 2][M / 2];
			int[][] temp3 = new int[N / 2][M / 2];
			int[][] temp4 = new int[N / 2][M / 2];

			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < M / 2; j++) {
					temp1[i][j] = A[i][j];
				}
			}
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < M / 2; j++) {
					temp2[i][j] = A[i][j + M / 2];
				}
			}
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < M / 2; j++) {
					temp4[i][j] = A[i + N / 2][j];
				}
			}
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < M / 2; j++) {
					temp3[i][j] = A[i + N / 2][j + M / 2];
				}
			}

			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < M / 2; j++) {
					temp[i][j + M / 2] = temp1[i][j];
				}
			}
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < M / 2; j++) {
					temp[i + N / 2][j + M / 2] = temp2[i][j];
				}
			}
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < M / 2; j++) {
					temp[i + N / 2][j] = temp3[i][j];
				}
			}
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < M / 2; j++) {
					temp[i][j] = temp4[i][j];
				}
			}

		} else {
			temp = new int[N][M];
			int[][] temp1 = new int[N / 2][M / 2];
			int[][] temp2 = new int[N / 2][M / 2];
			int[][] temp3 = new int[N / 2][M / 2];
			int[][] temp4 = new int[N / 2][M / 2];

			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < M / 2; j++) {
					temp1[i][j] = A[i][j];
				}
			}
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < M / 2; j++) {
					temp2[i][j] = A[i][j + M / 2];
				}
			}
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < M / 2; j++) {
					temp4[i][j] = A[i + N / 2][j];
				}
			}
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < M / 2; j++) {
					temp3[i][j] = A[i + N / 2][j + M / 2];
				}
			}

			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < M / 2; j++) {
					temp[i][j + M / 2] = temp3[i][j];
				}
			}
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < M / 2; j++) {
					temp[i + N / 2][j + M / 2] = temp4[i][j];
				}
			}
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < M / 2; j++) {
					temp[i + N / 2][j] = temp1[i][j];
				}
			}
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < M / 2; j++) {
					temp[i][j] = temp2[i][j];
				}
			}
		}

		return temp;
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		R = Integer.parseInt(tokens.nextToken());

		A = new int[N][M];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < M; j++) {
				A[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}

		Rs = new int[R];
		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < R; i++) {
			Rs[i] = Integer.parseInt(tokens.nextToken());
		} // 입력 끝.

//		System.out.println(Arrays.toString(Rs));//d

		for (int i = 0; i < R; i++) {
			A = operate(Rs[i]);
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				output.append(A[i][j]).append(" ");
			}
			output.append("\n");
		}

		System.out.print(output);

	}

}
