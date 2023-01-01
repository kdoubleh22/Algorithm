package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G5_01916 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m, _start, _dest, _answer;
	static List<Node>[] _graph;

	static void dijkstra() {
		int[] md = new int[_n + 1]; // minimum distance
		Arrays.fill(md, Integer.MAX_VALUE);
		md[_start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(_start, 0));

		while (!pq.isEmpty()) {
			Node polled = pq.poll();

			if (polled.cost > md[polled.num]) {
				continue;
			}

			if (polled.num == _dest) {
				_answer = polled.cost;
				return;
			}

			for (Node n : _graph[polled.num]) {
				if (polled.cost + n.cost < md[n.num]) {
					md[n.num] = polled.cost + n.cost;
					pq.offer(new Node(n.num, md[n.num]));
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());
		_m = Integer.parseInt(input.readLine());

		_graph = new List[_n + 1];
		for (int i = 1; i <= _n; i++) {
			_graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < _m; i++) {
			tokens = new StringTokenizer(input.readLine());
			int s = Integer.parseInt(tokens.nextToken());
			int e = Integer.parseInt(tokens.nextToken());
			int c = Integer.parseInt(tokens.nextToken());
			_graph[s].add(new Node(e, c));
		}

		tokens = new StringTokenizer(input.readLine());
		_start = Integer.parseInt(tokens.nextToken());
		_dest = Integer.parseInt(tokens.nextToken());

		_answer = -1;
		dijkstra();

		System.out.println(_answer);
	}

	static class Node implements Comparable<Node>{
		int num, cost;

		public Node(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}

	}

}
