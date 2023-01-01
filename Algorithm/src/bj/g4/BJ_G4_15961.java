package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ_G4_15961 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _d, _k, _c;
	static int[] order;
	static int[] backup;
	static Map<Integer, Integer> m;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_d = Integer.parseInt(tokens.nextToken());
		_k = Integer.parseInt(tokens.nextToken());
		_c = Integer.parseInt(tokens.nextToken());

		order = new int[_k];
		backup = new int[_k];

		m = new HashMap<Integer, Integer>();

		int max = Integer.MIN_VALUE;

		for (int i = 0; i < _k; i++) {
			int n = Integer.parseInt(input.readLine());
			order[i] = n;
			backup[i] = n;
			if (m.containsKey(n)) {
				m.put(n, m.get(n) + 1);
			} else {
				m.put(n, 1);
			}
		}

		int size = m.size();
		max = Math.max(max, m.containsKey(_c) ? size : size + 1);

		int idx = 0;
		for (int i = 0; i < _n - _k; i++) {
			int num = Integer.parseInt(input.readLine());
			// 있던걸 빼주고,
			if (m.get(order[idx]) == 1) {
				m.remove(order[idx]);
			} else {
				m.put(order[idx], m.get(order[idx]) - 1);
			}
			// 새로 입력받은걸 넣어줌.
			if (m.containsKey(num)) {
				m.put(num, m.get(num) + 1);
			} else {
				m.put(num, 1);
			}
			// order배열 업데이트.
			order[idx] = num;

			size = m.size();
			max = Math.max(max, m.containsKey(_c) ? size : size + 1);

			idx += 1;
			if (idx == _k) {
				idx = 0;
			}
		}

		for (int i = 0; i < _k - 1; i++) {
			int num = backup[i];
			// 있던걸 빼주고,
			if (m.get(order[idx]) == 1) {
				m.remove(order[idx]);
			} else {
				m.put(order[idx], m.get(order[idx]) - 1);
			}
			// 새로 입력받은걸 넣어줌.
			if (m.containsKey(num)) {
				m.put(num, m.get(num) + 1);
			} else {
				m.put(num, 1);
			}
			// order배열 업데이트.
			order[idx] = num;

			size = m.size();
			max = Math.max(max, m.containsKey(_c) ? size : size + 1);

			idx += 1;
			if (idx == _k) {
				idx = 0;
			}
		}

		System.out.println(max);

	}

}
