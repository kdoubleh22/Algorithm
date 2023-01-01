package bj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B2_2920 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		boolean asc = true;
		boolean desc = true;
		for (int i = 1; i <= 8; i++) {
			int num = Integer.parseInt(tokens.nextToken());
			if (num != i) {
				asc = false;
			}
			if (num != 9 - i) {
				desc = false;
			}
		}
		if (asc) {
			System.out.println("ascending");
		}
		if (desc) {
			System.out.println("descending");
		}
		if (!asc && !desc) {
			System.out.println("mixed");
		}
	}

}
