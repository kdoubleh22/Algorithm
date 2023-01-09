package bj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -문제
 * 단방향그래프
 * -풀이
 * a에서 b를 갈 수 있는지.
 */

public class BJ_S1_15723 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m;
	static List<Character>[] _graph;

	static boolean isReachable(char a, char b, boolean[] visited) {
		if (a == b) {
			return true;
		}
		boolean result = false;
		visited[a - 'a'] = true;

		for (char c : _graph[a - 'a']) {
			if (!visited[c - 'a']) {
				if (isReachable(c, b, visited)) {
					return true;
				}
			}
		}
		return result;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_graph = new List[26]; // 0은 a, 26은 z
		for (int i = 0; i < 26; i++) {
			_graph[i] = new LinkedList<>();
		}

		for (int i = 0; i < _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			char a = tokens.nextToken().charAt(0);
			tokens.nextToken();
			char b = tokens.nextToken().charAt(0);
			_graph[a - 'a'].add(b);
		}

		_m = Integer.parseInt(input.readLine());
		for (int i = 0; i < _m; i++) {
			tokens = new StringTokenizer(input.readLine());
			char a = tokens.nextToken().charAt(0);
			tokens.nextToken();
			char b = tokens.nextToken().charAt(0);

			if (isReachable(a, b, new boolean[26])) {
				output.append("T").append("\n");
			} else {
				output.append("F").append("\n");
			}
		}

		System.out.println(output);

	}

}
