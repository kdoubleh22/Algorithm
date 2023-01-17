package bj.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -문제
 * 양방향그래프
 * -풀이
 * 1. 플로이드워셜
 * 2. dfs로 그래프 탐색해서 도달 가능한지 파악
 */

public class BJ_G4_01976 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m;
	static int[][] _graph;
	static boolean[] _visited;

	static boolean dfs(int cur, int dest) {
//		System.out.println("cur:" + cur + " start:" + dest);

		_visited[cur] = true;

		boolean result = false;

		if (cur == dest) {
			return true;
		}

		for (int i = 1; i <= _n; i++) {
			if (_graph[cur][i] == 1 && !_visited[i]) {
				if (dfs(i, dest)) {
					return true;
				}
			}
		}

		return result;
	}

	public static void main(String[] args) throws Exception {
		_n = Integer.parseInt(input.readLine()); // 도시의 수
		_m = Integer.parseInt(input.readLine()); // 여행 계획에 속한 도시들의 수

		_graph = new int[_n + 1][_n + 1];
		for (int i = 1; i <= _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 1; j <= _n; j++) {
				_graph[i][j] = Integer.parseInt(tokens.nextToken());
			}
		} // 입력 끝.

		// 입력 확인.
//		for (int[] row : _graph) {
//			System.out.println(Arrays.toString(row));
//		}

		boolean isPossible = true;
		_visited = new boolean[_n + 1];
		tokens = new StringTokenizer(input.readLine());
		int start = Integer.parseInt(tokens.nextToken());
		for (int i = 1; i < _m; i++) {
			int dest = Integer.parseInt(tokens.nextToken());

			System.out.println("start:" + start + " dets:" + dest);

			if (!dfs(start, dest)) {
				isPossible = false;
				break;
			}

			start = dest;
		}

		if (isPossible) {
			output.append("YES");
		} else {
			output.append("NO");
		}

		System.out.println(output);
	}

}
