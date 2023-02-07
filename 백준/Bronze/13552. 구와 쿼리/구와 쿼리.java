import java.io.*;
import java.util.*;

/*
 * 100억
 */

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(input.readLine());

		long[][] points = new long[N][3];

		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(input.readLine());
			points[i][0] = Long.parseLong(tokens.nextToken());
			points[i][1] = Long.parseLong(tokens.nextToken());
			points[i][2] = Long.parseLong(tokens.nextToken());
		}

		int M = Integer.parseInt(input.readLine());

		while (M-- > 0) {
			tokens = new StringTokenizer(input.readLine());
			long x = Long.parseLong(tokens.nextToken());
			long y = Long.parseLong(tokens.nextToken());
			long z = Long.parseLong(tokens.nextToken());
			long r = Long.parseLong(tokens.nextToken());
			int answer = 0;
			Long rSquare = r * r;
			for (int i = 0; i < N; i++) {
				if ((x - points[i][0]) * (x - points[i][0]) + (y - points[i][1]) * (y - points[i][1])
						+ (z - points[i][2]) * (z - points[i][2]) <= rSquare) {
					answer += 1;
				}
			}
			output.append(answer).append("\n");
		}

		System.out.println(output);
	}

}