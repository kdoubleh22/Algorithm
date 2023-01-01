package bj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B1_2563 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int n;
	static boolean[][] paper;

	public static void main(String[] args) throws NumberFormatException, IOException {
		n = Integer.parseInt(input.readLine());
		paper = new boolean[100][100];

		for (int tc = 0; tc < n; tc++) {
			tokens = new StringTokenizer(input.readLine());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());
			for (int r = 99 - y; r > 99 - y - 10; r--) {
				for (int c = x; c < x + 10; c++) {
					paper[r][c] = true;
				}
			}
		}

		int answer = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (paper[i][j]) {
					answer++;
				}
			}
		}

		output.append(answer);
		System.out.print(output);

	}

}
