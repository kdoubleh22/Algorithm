package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author me
 * @see https://www.acmicpc.net/problem/2164
 * @performance 14600 KB 9204 ms
 * @category #브루트포스 알고리즘 #백트래킹
 * @memo
 * 
 */

public class BJ_G4_09663 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();

	static int[][] diagonalDeltas = { { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };

	static int n, answer;
	static boolean[][] board;

	static boolean col(int r, int c) {
		int saveR = r;
		r = r - 1;
		while (r >= 0) {
			if (board[r][c] == true) {
				return false;
			}
			r = r - 1;
		}
		r = saveR;
		r = r + 1;
		while (r < n) {
			if (board[r][c] == true) {
				return false;
			}
			r = r + 1;
		}
		return true;
	}

	static boolean diagonal(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int j = 1;
			while (true) {
				int nr = r + j * diagonalDeltas[i][0];
				int nc = c + j * diagonalDeltas[i][1];
				if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
					if (board[nr][nc] == true) {
						return false;
					}
					j++;
				} else {
					break;
				}
			}
		}
		return true;
	}

	static void recursion(int cnt) {
		if (cnt == n) {
			answer += 1;
			return;
		}
		for (int i = 0; i < n; i++) {
			if (col(cnt, i) && diagonal(cnt, i)) {
				board[cnt][i] = true;
				recursion(cnt + 1);
				board[cnt][i] = false;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		n = Integer.parseInt(input.readLine());

		board = new boolean[n][n];
		answer = 0;

		recursion(0);
		output.append(answer);
		System.out.print(output);
	}

}
