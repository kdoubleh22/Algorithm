package bj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G3_01238 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m, _x;
	static List<Node>[] _graph;
	static int[] _mdToX, _mdFromX; // _mdToX:minimum distance to X

	static void dijkstraToX(int start) {
		int[] md = new int[_n + 1];
		Arrays.fill(md, Integer.MAX_VALUE);
		md[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node polled = pq.poll();

			if (polled.weight > md[polled.idx]) {
				continue;
			}

			if (polled.idx == _x) {
				_mdToX[start] = polled.weight;
				return;
			}

			for (Node n : _graph[polled.idx]) {
				if (polled.weight + n.weight < md[n.idx]) {
					md[n.idx] = polled.weight + n.weight;
					pq.offer(new Node(n.idx, md[n.idx]));
				}
			}

		}
	}

	static void dijkstraFromX() {
		_mdFromX = new int[_n + 1];
		Arrays.fill(_mdFromX, Integer.MAX_VALUE);
		_mdFromX[_x] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(_x, 0));

		while (!pq.isEmpty()) {
			Node polled = pq.poll();

			if (polled.weight > _mdFromX[polled.idx]) {
				continue;
			}

			for (Node n : _graph[polled.idx]) {
				if (polled.weight + n.weight < _mdFromX[n.idx]) {
					_mdFromX[n.idx] = polled.weight + n.weight;
					pq.offer(new Node(n.idx, _mdFromX[n.idx]));
				}
			}

		}
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());
		_x = Integer.parseInt(tokens.nextToken());

		_graph = new List[_n + 1];
		for (int i = 1; i <= _n; i++) {
			_graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < _m; i++) {
			tokens = new StringTokenizer(input.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			int weight = Integer.parseInt(tokens.nextToken());
			_graph[from].add(new Node(to, weight));
		}

		_mdToX = new int[_n + 1];
		for (int i = 1; i <= _n; i++) {
			dijkstraToX(i);
		}

		dijkstraFromX();

		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= _n; i++) {
			if (_mdFromX[i] + _mdToX[i] > max) {
				max = _mdFromX[i] + _mdToX[i];
			}
		}

		System.out.println(max);

	}

	static class Node implements Comparable<Node> {
		int idx, weight;

		public Node(int idx, int weight) {
			this.idx = idx;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}

	}

}
