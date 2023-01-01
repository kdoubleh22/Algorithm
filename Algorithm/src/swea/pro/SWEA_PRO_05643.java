package swea.pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_PRO_05643 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _t, _n, _m, _dfsResult;
	static List<Integer>[] _shorter, _taller;
	static int[] _in, _out;
	static boolean[] _isVisited;
	static boolean[][] _totalVisited;

	static void dfs(List<Integer>[] graph, int cur, boolean[] visited) {
		visited[cur] = true;
		_dfsResult++;

		for (int i : graph[cur]) {
			if (!visited[i]) {
				dfs(graph, i, visited);
			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_t = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= _t; tc++) {
			_n = Integer.parseInt(input.readLine());
			_m = Integer.parseInt(input.readLine());

			_shorter = new List[_n + 1];
			_taller = new List[_n + 1];
			for (int i = 1; i <= _n; i++) {
				_shorter[i] = new ArrayList<>();
				_taller[i] = new ArrayList<>();
			}

			_in = new int[_n + 1];
			_out = new int[_n + 1];

			for (int i = 0; i < _m; i++) {
				tokens = new StringTokenizer(input.readLine());
				int a = Integer.parseInt(tokens.nextToken());
				int b = Integer.parseInt(tokens.nextToken());
				_shorter[a].add(b);
				_taller[b].add(a);
			}

			int answer = 0;
			for (int i = 1; i <= _n; i++) {
				_dfsResult = -1;
				dfs(_taller, i, new boolean[_n + 1]);
				_in[i] = _dfsResult;
				_dfsResult = -1;
				dfs(_shorter, i, new boolean[_n + 1]);
				_out[i] = _dfsResult;
				if (_in[i] + _out[i] == _n - 1) {
					answer++;
				}
			}

			output.append("#").append(tc).append(" ").append(answer).append("\n");

		}

		System.out.println(output);

	}

}
