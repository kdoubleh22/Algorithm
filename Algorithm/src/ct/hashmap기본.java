package ct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class hashmap기본 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static Map<Integer, Integer> _hashMap;

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_hashMap = new HashMap<>();
		while (_n-- > 0) {
			tokens = new StringTokenizer(input.readLine());
			String cmd = tokens.nextToken();
			command(cmd, tokens);
		}

		System.out.println(output);
	}

	private static void command(String cmd, StringTokenizer tokens) {
		if (cmd.equals("add")) {
			int key = Integer.parseInt(tokens.nextToken());
			int value = Integer.parseInt(tokens.nextToken());
			_hashMap.put(key, value);
		} else if (cmd.equals("find")) {
			int key = Integer.parseInt(tokens.nextToken());
			if (_hashMap.containsKey(key)) {
				output.append(_hashMap.get(key));
			} else {
				output.append("None");
			}
			output.append("\n");

		} else if (cmd.equals("remove")) {
			int key = Integer.parseInt(tokens.nextToken());
			_hashMap.remove(key);
		}

	}

}
