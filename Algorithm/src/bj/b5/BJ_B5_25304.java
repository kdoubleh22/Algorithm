package bj.b5;

import java.io.*;
import java.util.*;

public class BJ_B5_25304 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;

	public static void main(String[] args) throws Exception {
		int X = Integer.parseInt(input.readLine());
		int N = Integer.parseInt(input.readLine());

		int price = 0;
		while (N-- > 0) {
			tokens = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			price += a * b;
		}

		if (price == X) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}

	}

}
