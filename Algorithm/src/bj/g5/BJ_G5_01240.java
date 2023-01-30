package bj.g5;

import java.io.*;
import java.util.*;

/*
 * -문제
 * 다익스트라
 * -풀이
 * 다익스트라
 */

public class BJ_G5_01240 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n; // 2 <= N <= 1000
	static int _m; // M <= 1000
	static List<Node>[] _graph;

	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());

		_graph = new List[_n + 1];
		for (int i = 0; i < _n + 1; i++) {
			_graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < _n - 1; i++) {
			tokens = new StringTokenizer(input.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			int weight = Integer.parseInt(tokens.nextToken());

			_graph[from].add(new Node(to, weight));
			_graph[to].add(new Node(from, weight));
		}

		while (_m-- > 0) {
			tokens = new StringTokenizer(input.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());

			output.append(dijkstra(from, to)).append("\n");
		}

		System.out.println(output);
	}

	private static int dijkstra(int start, int goal) {
		int result = -1;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] d = new int[_n + 1];
		Arrays.fill(d, Integer.MAX_VALUE);

		pq.add(new Node(start, 0));
		d[start] = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (cur.index == goal) {
				result = cur.cost;
				break;
			}

			// 방문처리 대신 이미 처리된 것인지 확인함.
			if (d[cur.index] < cur.cost) {
				continue;
			}

			for (Node node : _graph[cur.index]) {
				// 현재까지의 비용과 해당 노드까지의 비용의 합
				int newCost = cur.cost + node.cost;
				// 더 적은 비용으로 갈 수 있으면 업데이트.
				if (newCost < d[node.index]) {
					d[node.index] = newCost;
					pq.add(new Node(node.index, newCost));
				}
			}
		}

		return result;
	}

	static class Node implements Comparable<Node> {
		int index;
		int cost; // distance

		public Node(int cost, int dist) {
			this.index = cost;
			this.cost = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}

	}
}