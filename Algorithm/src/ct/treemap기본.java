package ct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class treemap기본 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static Map<Integer, Integer> _treeMap;

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_treeMap = new TreeMap<>();
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
			_treeMap.put(key, value);
		} else if (cmd.equals("find")) {
			int key = Integer.parseInt(tokens.nextToken());
			if (_treeMap.containsKey(key)) {
				output.append(_treeMap.get(key));
			} else {
				output.append("None");
			}
			output.append("\n");

		} else if (cmd.equals("remove")) {
			int key = Integer.parseInt(tokens.nextToken());
			_treeMap.remove(key);
		} else if (cmd.equals("print_list")) {
			if (_treeMap.isEmpty()) {
				output.append("None");
			}
			for (int key : _treeMap.keySet()) {
				output.append(_treeMap.get(key)).append(" ");
			}
			output.append("\n");
		}

	}

}
