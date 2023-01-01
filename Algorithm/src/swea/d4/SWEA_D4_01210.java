package swea.d4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D4_01210 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int answer;

	static void ladder(int[][] ladders, int r, int c, int direction) { // 방향: 4-left,8-up,6-right
		if (r == 0) {
			answer = c;
			return;
		}
		if (direction != 6 && c - 1 >= 0 && ladders[r][c - 1] == 1) { // left
			ladder(ladders, r, c - 1, 4);
		} else if (direction != 4 && c + 1 < 100 && ladders[r][c + 1] == 1) { // right
			ladder(ladders, r, c + 1, 6);
		} else { // up
			ladder(ladders, r - 1, c, 8);
		}
	}

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new FileReader("./swea_01210_input.txt"));// d
		for (int i = 0; i < 10; i++) {
			tokens = new StringTokenizer(input.readLine());
			int tc = Integer.parseInt(tokens.nextToken());
			int[][] ladders = new int[100][100];
			for (int j = 0; j < 100; j++) {
				tokens = new StringTokenizer(input.readLine(), " ");
				for (int k = 0; k < 100; k++) {
					ladders[j][k] = Integer.parseInt(tokens.nextToken());
				}
			}
//			for(int j=0;j<100;j++) {
//				System.out.println(Arrays.toString(ladders[j]));
//			}
			for (int j = 0; j < 100; j++) {
				if (ladders[99][j] == 2) {
					ladder(ladders, 99, j, 8);
					break;
				}
			}
			output.append(String.format("#%d %d%n", tc, answer));
		}
		System.out.print(output);
	}

}
