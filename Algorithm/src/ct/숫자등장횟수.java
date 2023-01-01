package ct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 숫자등장횟수 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		int n = Integer.parseInt(tokens.nextToken());
		int m = Integer.parseInt(tokens.nextToken());

		Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			int key = Integer.parseInt(tokens.nextToken());
			if (hashMap.containsKey(key)) {
				hashMap.put(key, hashMap.get(key) + 1);
			} else {
				hashMap.put(key, 1);
			}
		}

		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < m; i++) {
			int key = Integer.parseInt(tokens.nextToken());
			if (hashMap.containsKey(key)) {
				output.append(hashMap.get(key)).append(" ");
			} else {
				output.append(0).append(" ");
			}
		}
		
		System.out.println(output);

	}

}
