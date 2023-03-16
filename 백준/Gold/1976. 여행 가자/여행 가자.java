import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m;
	static int[][] _graph;
	static int[] _parent;
	static boolean[] _visited;

	static int find(int x) {
		if (x == _parent[x]) {
			return x;
		}
		return _parent[x] = find(_parent[x]);
	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y) {
			return;
		}
		if (x < y) {
			_parent[y] = x;
		} else {
			_parent[x] = y;
		}
	}

	static boolean isUnion(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y) {
			return true;
		} else {
			return false;
		}
	}

	static void dfs(int cur) {
//		System.out.println("cur:" + cur + " start:" + dest);

		_visited[cur] = true;

		for (int i = 1; i <= _n; i++) {
			if (_graph[cur][i] == 1 && !_visited[i]) {
				union(cur, i);
				dfs(i);
			}
		}

	}

	public static void main(String[] args) throws Exception {
		_n = Integer.parseInt(input.readLine());
		_m = Integer.parseInt(input.readLine());

		_graph = new int[_n + 1][_n + 1];
		_parent = new int[_n + 1];

		for (int i = 1; i <= _n; i++) {
			_parent[i] = i;
		}

		for (int i = 1; i <= _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 1; j <= _n; j++) {
				_graph[i][j] = Integer.parseInt(tokens.nextToken());
			}
		} // 입력 끝.

		// 전처리
		_visited = new boolean[_n + 1];
		for (int i = 1; i <= _n; i++) {
			if (!_visited[i]) {
				dfs(i);
			}
		}

		boolean isPossible = true;
		tokens = new StringTokenizer(input.readLine());
		int start = Integer.parseInt(tokens.nextToken());
		for (int i = 1; i < _m; i++) {
			int dest = Integer.parseInt(tokens.nextToken());

			if (!isUnion(start, dest)) {
				isPossible = false;
				break;
			}

			start = dest;
		}

//		System.out.println(Arrays.toString(_parent));

		if (isPossible) {
			output.append("YES");
		} else {
			output.append("NO");
		}

		System.out.println(output);
	}

}