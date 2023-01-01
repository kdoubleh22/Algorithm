package ct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class treeset기본 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static TreeSet<Integer> _ts;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int n = Integer.parseInt(input.readLine());
		_ts = new TreeSet<>();
		while (n-- > 0) {
			tokens = new StringTokenizer(input.readLine());
			String cmd = tokens.nextToken();
			int num = 0;
			if (tokens.hasMoreTokens()) {
				num = Integer.parseInt(tokens.nextToken());
			}
			command(cmd, num);
		}

		System.out.println(output);

	}

	private static void command(String cmd, int num) {
		if (num == 0) { // largest smallest
			if (cmd.equals("largest")) {
				if (_ts.isEmpty()) {
					output.append("None").append("\n");
				} else {
					output.append(_ts.last()).append("\n");
				}
			} else {
				if (_ts.isEmpty()) {
					output.append("None").append("\n");
				} else {
					output.append(_ts.first()).append("\n");
				}
			}
		} else { // add remove find lower_bound upper_bound
			if (cmd.equals("add")) {
				_ts.add(num);
			} else if (cmd.equals("remove")) {
				_ts.remove(num);
			} else if (cmd.equals("find")) {
				if (_ts.contains(num)) {
					output.append("true").append("\n");
				} else {
					output.append("false").append("\n");
				}
			} else if (cmd.equals("lower_bound")) {
				Integer lb = _ts.ceiling(num);
				if (lb == null) {
					output.append("None").append("\n");
				} else {
					output.append(lb).append("\n");
				}
			} else {
				Integer higher = _ts.higher(num);
				if (higher == null) {
					output.append("None").append("\n");
				} else {
					output.append(higher).append("\n");
				}
			}
		}

	}

}
