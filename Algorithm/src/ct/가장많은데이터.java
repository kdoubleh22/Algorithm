package ct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 가장많은데이터 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int n = Integer.parseInt(input.readLine());

		Map<String, Integer> hashMap = new HashMap<String, Integer>();

		int max = Integer.MIN_VALUE;
		String answer = null;
		while (n-- > 0) {
			String key = input.readLine();
			if (hashMap.containsKey(key)) {
				hashMap.put(key, hashMap.get(key) + 1);
				max = Math.max(max, hashMap.get(key));
			} else {
				hashMap.put(key, 1);
				max = Math.max(max, hashMap.get(key));
			}
		}

		System.out.println(max);

	}

}
