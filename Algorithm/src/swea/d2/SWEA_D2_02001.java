package swea.d2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D2_02001 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws NumberFormatException, IOException {
//		input = new BufferedReader(new FileReader("./swea02001input.txt"));// d
		int t = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= t; tc++) {
			tokens = new StringTokenizer(input.readLine());
			int n = Integer.parseInt(tokens.nextToken());
			int m = Integer.parseInt(tokens.nextToken());
			int[][] map = new int[n][n];

			for (int i = 0; i < n; i++) {
				tokens = new StringTokenizer(input.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}

			int max = Integer.MIN_VALUE;
			for (int i = 0; i <= n - m; i++) {
				for (int j = 0; j <= n - m; j++) {
					int sum = 0;
					for (int x = 0; x < m; x++) {
						for (int y = 0; y < m; y++) {
//							System.out.println(i + ":" + j + ":" + x + ":" + y);// d
							sum += map[i + x][j + y];
						}
					}
					if (sum > max) {
						max = sum;
					}
				}
			}
			output.append("#").append(tc).append(" ").append(max).append("\n");

		} // tc
		System.out.print(output);
	}

}
