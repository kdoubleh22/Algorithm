package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_G417471 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _answer, _cnt, _popSum, _dfsResult;
	static int[] _pops; // populations
	static List<Integer>[] _graph;
	static boolean[] _isConnected1, _isConnected2;
	static boolean _isFound;

	static void dfs(int cur, int[] result, int size, boolean[] isVisited, boolean[] isConnected) {
		isVisited[cur] = true;
		_cnt++;
		_popSum += _pops[cur];

		if (_cnt == size) {
			_isFound = true;
			_dfsResult = _popSum;
			return;
		}

		for (int next : _graph[cur]) {
			if (isConnected[next] && !isVisited[next]) {
				dfs(next, result, size, isVisited, isConnected);
				if (_isFound) {
					return;
				}
			}
		}
	}

	static void powerset(int cnt, int[] result1, int size1, int[] result2, int size2) {
		if (cnt == _n + 1) {
			if (size1 == 0 || size2 == 0) {
				return;
			}

			_isConnected1 = new boolean[_n + 1];
			for (int i = 0; i < size1; i++) {
				_isConnected1[result1[i]] = true;
			}
			_popSum = 0;
			_cnt = 0;
			_dfsResult = -1;
			_isFound = false;
			dfs(result1[0], result1, size1, new boolean[_n + 1], _isConnected1);
			int dfs1 = _dfsResult;
			if (dfs1 == -1) {
				return;
			}

			_isConnected2 = new boolean[_n + 1];
			for (int i = 0; i < size2; i++) {
				_isConnected2[result2[i]] = true;
			}
			_popSum = 0;
			_cnt = 0;
			_dfsResult = -1;
			_isFound = false;
			dfs(result2[0], result2, size2, new boolean[_n + 1], _isConnected2);
			int dfs2 = _dfsResult;
			if (dfs2 == -1)
				return;

			_answer = Math.min(_answer, Math.abs(dfs1 - dfs2));

			return;
		}

		result1[size1] = cnt;
		powerset(cnt + 1, result1, size1 + 1, result2, size2);
		result2[size2] = cnt;
		powerset(cnt + 1, result1, size1, result2, size2 + 1);

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_pops = new int[_n + 1];
		tokens = new StringTokenizer(input.readLine());
		for (int i = 1; i <= _n; i++) {
			_pops[i] = Integer.parseInt(tokens.nextToken());
		}

		_graph = new List[_n + 1];
		for (int i = 1; i <= _n; i++) {
			_graph[i] = new ArrayList<>();
		}
		for (int i = 1; i <= _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			int num = Integer.parseInt(tokens.nextToken());
			while (num-- > 0) {
				int tmp = Integer.parseInt(tokens.nextToken());
				_graph[i].add(tmp);
			}
		} // 입력 끝.

		_answer = Integer.MAX_VALUE;
		powerset(1, new int[_n], 0, new int[_n], 0);

		if (_answer == Integer.MAX_VALUE) {
			_answer = -1;
		}

		output.append(_answer);
		System.out.println(output);

	}

}
