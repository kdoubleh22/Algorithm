import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -문제
 * 방향있음. 
 * -풀이
 * 자신이 몇 번째만에 도달할 수 있는지.
 * ->자식이 몇 명인지?
 * ->그럼 방향을 거꾸로해서 depth를 따져봐도 되겠다.
 * ->왔던 노드는 또 올 필요 없으니까 memoization으로 dp적으로 접근도 가능.
 * dfs+memoization.
 */

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n; // 과목의 수 (1 <= N <= 1000)
	static int _m; // 조건의 수 (0 <= M <= 500,000)
	static List<Integer>[] _graph;
	static int[] _memo; // memoization

	// dfs는 depth를 구하고, memoize한다.
	static int dfs(int cur, boolean[] visited) {
		// 이미 왔던 곳이면 바로 리턴.
		if (_memo[cur] != 0) {
			return _memo[cur];
		}

		int cnt = 1;
		visited[cur] = true;

		int max = Integer.MIN_VALUE;

		for (int next : _graph[cur]) {
			if (!visited[next]) {
				max = Math.max(max, dfs(next, visited));
			}
		}

		if (max != Integer.MIN_VALUE) {
			return _memo[cur] = cnt + max;
		} else {
			return _memo[cur] = cnt;
		}

	}

	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());

		_graph = new List[_n + 1];

		while (_m-- > 0) {
			tokens = new StringTokenizer(input.readLine());
			int to = Integer.parseInt(tokens.nextToken());
			int from = Integer.parseInt(tokens.nextToken());

			if (_graph[from] == null) {
				_graph[from] = new ArrayList<Integer>();
			}

			_graph[from].add(to);
		}

		_memo = new int[_n + 1];

		for (int i = 1; i < _n + 1; i++) {
			if (_graph[i] == null) { // 선수과목이 없는 경우.
				_memo[i] = 1;
			} else {
				_memo[i] = dfs(i, new boolean[_n + 1]);
			}
			output.append(_memo[i]).append(" ");
		}

		System.out.println(output);

	}

}