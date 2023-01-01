package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ_G4_17140 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int r, c, k;
	static int[][] A;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		r = Integer.parseInt(tokens.nextToken());
		c = Integer.parseInt(tokens.nextToken());
		k = Integer.parseInt(tokens.nextToken());

		A = new int[100][100];

		for (int i = 0; i < 3; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < 3; j++) {
				A[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}

		int rows = 3, cols = 3;
		Map<Integer, Integer> count = new HashMap<>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
			}
		}

	}

}
