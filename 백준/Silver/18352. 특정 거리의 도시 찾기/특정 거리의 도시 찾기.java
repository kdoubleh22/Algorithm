import java.io.*;
import java.util.*;

/*
 * bfs
 */

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n; // 도시의 개수
	static int _m; // 도로의 개수
	static int _k; // 거리 정보
	static int _x; // 출발 도시의 번호
	static List<Integer>[] _graph;
	static List<Integer> _answer;

	static void bfs() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[_n + 1];

		pq.add(new Node(_x, 0));
		visited[_x] = true;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int index = cur.index;
			int cost = cur.cost;

			if (cost == _k) {
				_answer.add(index);
				continue;
			}

			for (int n : _graph[index]) {
				if (!visited[n]) {
					pq.add(new Node(n, cost + 1));
					visited[n] = true;
				}
			}
		}

	}

	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken()); // 도시의 개수
		_m = Integer.parseInt(tokens.nextToken()); // 도로의 개수
		_k = Integer.parseInt(tokens.nextToken()); // 거리 정보
		_x = Integer.parseInt(tokens.nextToken()); // 출발 도시의 번호

		_graph = new List[_n + 1];
		for (int i = 1; i <= _n; i++) {
			_graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < _m; i++) {
			tokens = new StringTokenizer(input.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());

			_graph[from].add(to);
		}

		_answer = new ArrayList<>();
		bfs();

		Collections.sort(_answer);

		if (_answer.size() == 0) {
			System.out.println(-1);
		} else {
			for (int i : _answer) {
				System.out.println(i);
			}
		}
	}

	static class Node implements Comparable<Node> {
		int index;
		int cost;

		public Node(int index, int cost) {
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

}