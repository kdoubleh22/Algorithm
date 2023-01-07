package bj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -문제 요약
 * 사이클이 없는 그래프
 * 친분관계는 양방향
 * 튜터 튜티 관계는단방향
 * 튜터가 튜티에게만 전달할 수 있음
 * -풀이
 * 트리가 몇개인지 세고, 각 트리의 vertex 개수를 곱한다.
 * (a*b*c)%c=((a*b)%c*c%c)%c
 */

public class BJ_S1_24542 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m;
	static List<Integer>[] _relations;
	static boolean[] _visited;
	static int _cnt;
	static long _answer;

	static void dfs(int cur) {
		_cnt += 1;
		_visited[cur] = true;
		for (int node : _relations[cur]) {
			if (!_visited[node]) {
				dfs(node);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());

		_relations = new List[_n + 1];
		for (int i = 1; i <= _n; i++) {
			_relations[i] = new ArrayList<>();
		}

		for (int i = 0; i < _m; i++) {
			tokens = new StringTokenizer(input.readLine());
			int u = Integer.parseInt(tokens.nextToken());
			int v = Integer.parseInt(tokens.nextToken());
			_relations[u].add(v);
			_relations[v].add(u);
		}

		_visited = new boolean[_n + 1];
		_answer = 1;
		for (int i = 1; i <= _n; i++) {
			// 연결된 node가 있고, 방문한 적이 없으면,
			if (!_relations[i].isEmpty()) {
				if (!_visited[i]) {
					_cnt = 0;
					dfs(i);
					_answer = (_answer * _cnt) % 1000000007;
				}
			}
		}

		System.out.println(_answer);

	}

}
