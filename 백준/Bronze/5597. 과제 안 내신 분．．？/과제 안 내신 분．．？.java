import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		boolean[] check = new boolean[31];
		for (int i = 0; i < 28; i++) {
			int n = Integer.parseInt(input.readLine());
			check[n] = true;
		}

		for (int i = 1; i < 31; i++) {
			if (!check[i]) {
				System.out.println(i);
			}
		}
	}

}