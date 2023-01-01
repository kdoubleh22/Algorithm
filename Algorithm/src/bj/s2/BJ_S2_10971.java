package bj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_S2_10971 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _answer;
	static int[][] _graph;
	static boolean[] _visited;

	static void dfs(int cnt, int cur, int sum) {
		_visited[cur] = true;

		if (sum >= _answer) {
			_visited[cur] = false;
			return;
		}

		if (cnt == _n - 1) {
			if (_graph[cur][0] != 0) {
				_answer = Math.min(_answer, sum + _graph[cur][0]);
			}
			_visited[cur] = false;
			return;
		}

		for (int i = 0; i < _n; i++) {
			if (!_visited[i] && _graph[cur][i] != 0) { // 방문한 적 없고, 길이 있고, 자기 자신이 아니라면,
//				_visited[i] = true;
				dfs(cnt + 1, i, sum + _graph[cur][i]);
//				_visited[i] = false;
			}
		}

		_visited[cur] = false;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_graph = new int[_n][_n];
		for (int i = 0; i < _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < _n; j++) {
				_graph[i][j] = Integer.parseInt(tokens.nextToken());
			}
		} // 입력 끝

		_answer = Integer.MAX_VALUE;
		_visited = new boolean[_n];
//		_visited[0] = true;
		dfs(0, 0, 0); // 0에서 시작.

		output.append(_answer);
		System.out.println(output);

	}

}
