import java.io.*;
import java.util.*;

/*
 * -문제
 * -풀이
 * 한 노드에서 가장 먼 노드를 골라서, 그 노드에서 가장 먼 거리를 구함.
 */

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _v;
	static List<To>[] _tree;
	static int _maxIndex;
	static int _maxDistance;

	static void dfs(int cur, boolean[] visited, int distance) {
		visited[cur] = true;

		if (distance > _maxDistance) {
			_maxIndex = cur;
			_maxDistance = distance;
		}

		for (To to : _tree[cur]) {
			if (!visited[to.index]) {
				dfs(to.index, visited, distance + to.weight);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		_v = Integer.parseInt(input.readLine());

		_tree = new List[_v + 1];

		for (int i = 0; i < _v; i++) {
			tokens = new StringTokenizer(input.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			while (true) {
				int to = Integer.parseInt(tokens.nextToken());
				if (to == -1) {
					break;
				}
				int weight = Integer.parseInt(tokens.nextToken());

				if (_tree[from] == null) {
					_tree[from] = new ArrayList<>();
				}
				if (_tree[to] == null) {
					_tree[to] = new ArrayList<>();
				}

				_tree[from].add(new To(to, weight));
				_tree[to].add(new To(from, weight));
			}
		}

		// 아무 노드에서 시작해서 가장 먼 노드를 고름.
		_maxIndex = -1;
		_maxDistance = -1;
		dfs(1, new boolean[_v + 1], 0);

		// 그 노드에서 가장 먼 거리를 구함.
		dfs(_maxIndex, new boolean[_v + 1], 0);

		System.out.println(_maxDistance);
	}

	static class To {
		int index;
		int weight;

		public To(int index, int weight) {
			this.index = index;
			this.weight = weight;
		}

	}

}