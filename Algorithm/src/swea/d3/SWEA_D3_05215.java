package swea.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D3_05215 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int N, L, answer;
	static int[][] sNc;
	static boolean[] isSelected;

	static void subSet(int cnt, int score, int calorie) {
		if (calorie > L) {
			return;
		}
		if (cnt == N) {
			if (score == 0) {
				return;
			}
			if (score > answer) {
				answer = score;
			}
			return;
		}

		subSet(cnt + 1, score + sNc[cnt][0], calorie + sNc[cnt][1]);
		subSet(cnt + 1, score, calorie);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
//		input = new BufferedReader(new FileReader("./swea05215input.txt"));// d

		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			tokens = new StringTokenizer(input.readLine());
			N = Integer.parseInt(tokens.nextToken());
			L = Integer.parseInt(tokens.nextToken());

			sNc = new int[N][L];
			for (int i = 0; i < N; i++) {
				tokens = new StringTokenizer(input.readLine());
				sNc[i][0] = Integer.parseInt(tokens.nextToken());
				sNc[i][1] = Integer.parseInt(tokens.nextToken());
			}

			isSelected = new boolean[N];
			answer = Integer.MIN_VALUE;
			
			subSet(0, 0, 0);

			output.append("#").append(tc).append(" ").append(answer).append("\n");

		}
		System.out.print(output);

	}

}
