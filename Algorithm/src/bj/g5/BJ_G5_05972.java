package bj.g5;

import java.io.*;
import java.util.*;

/*
 * 다익스트라
 */

public class BJ_G5_05972 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static int _m;
	static List<Node>[] _graph;
	static int _answer;

	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] costs = new int[_n + 1];
		Arrays.fill(costs, Integer.MAX_VALUE);

		pq.add(new Node(1, 0));
		costs[1] = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int index = cur.index;
			int cost = cur.cost;

			// 종료 조건
			if (index == _n) {
				_answer = cost;
				return;
			}

			// 방문 확인
			if (costs[index] < cost) {
				continue;
			}

			for (Node n : _graph[index]) {
				if (cost + n.cost < costs[n.index]) {
					costs[n.index] = cost + n.cost;
					pq.add(new Node(n.index, costs[n.index]));
				}
			}
		}

	}

	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());

		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());

		_graph = new List[_n + 1];
		for (int i = 1; i <= _n; i++) {
			_graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < _m; i++) {
			tokens = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			int c = Integer.parseInt(tokens.nextToken());
			_graph[a].add(new Node(b, c));
			_graph[b].add(new Node(a, c));
		}

		_answer = -1;
		dijkstra();

		System.out.println(_answer);
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