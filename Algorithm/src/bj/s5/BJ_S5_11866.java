package bj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_S5_11866 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _k;
	static List<Integer> _circle;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_k = Integer.parseInt(tokens.nextToken());

		_circle = new LinkedList<>();
		for (int i = 0; i <= _n; i++) {
			_circle.add(i);
		}

		output.append("<");

		int idx = -1;
		while (_circle.size()!=1) {
			idx+=_k;
			output.append(_circle.remove((idx)%_circle.size())+1);
			output.append(", ");
		}
		output.append(_circle.get(0));
		output.append(">");
		
		System.out.println(output);

	}

}
