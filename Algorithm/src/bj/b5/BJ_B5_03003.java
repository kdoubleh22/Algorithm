package bj.b5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B5_03003 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());

		int king = Integer.parseInt(tokens.nextToken());
		int queen = Integer.parseInt(tokens.nextToken());
		int rook = Integer.parseInt(tokens.nextToken());
		int bishop = Integer.parseInt(tokens.nextToken());
		int knight = Integer.parseInt(tokens.nextToken());
		int pawn = Integer.parseInt(tokens.nextToken());

		output.append(1 - king).append(" ");
		output.append(1 - queen).append(" ");
		output.append(2 - rook).append(" ");
		output.append(2 - bishop).append(" ");
		output.append(2 - knight).append(" ");
		output.append(8 - pawn).append(" ");

		System.out.println(output);
	}

}
