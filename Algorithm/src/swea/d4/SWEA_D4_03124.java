package swea.d4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * @author SSAFY
 * @memo 그래프에서 단방향이라는 말이 없고, 연결되어있다고 하면 양방향 그래프로 생각.
 */

public class SWEA_D4_03124 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _t, _v, _e;
	static List<Node>[] _nodes;
	static Edge[] _edges;
	static int[] _p;

	static class Node implements Comparable<Node> {
		int no, weight;

		public Node(int no, int weight) {
			this.no = no;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}

	}

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
//		input = new BufferedReader(new FileReader("./io/swea03124input.txt"));
		_t = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= _t; tc++) {
			tokens = new StringTokenizer(input.readLine());
			_v = Integer.parseInt(tokens.nextToken());
			_e = Integer.parseInt(tokens.nextToken());

			 //prim
			_nodes = new List[_v + 1];
			for (int i = 1; i <= _v; i++) {
				_nodes[i] = new ArrayList<>();
			}

			for (int i = 0; i < _e; i++) {
				tokens = new StringTokenizer(input.readLine());
				int from = Integer.parseInt(tokens.nextToken());
				int to = Integer.parseInt(tokens.nextToken());
				int weight = Integer.parseInt(tokens.nextToken());
				_nodes[from].add(new Node(to, weight));
				_nodes[to].add(new Node(from, weight));
			}

			// kruskal
			_edges = new Edge[_e];
			for (int i = 0; i < _e; i++) {
				tokens = new StringTokenizer(input.readLine());
				int from = Integer.parseInt(tokens.nextToken());
				int to = Integer.parseInt(tokens.nextToken());
				int weight = Integer.parseInt(tokens.nextToken());
				_edges[i] = new Edge(from, to, weight);
			}

//			long answer = prim();
			long answer = kruskal();

			output.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.println(output);
	}

	private static long kruskal() {
		long totalWeight = 0;
		int edgeCnt = 0;
		make();
		Arrays.sort(_edges);
		for (Edge e : _edges) {
			if (find(e.from) != find(e.to)) {
				union(e.from, e.to);
				totalWeight += e.weight;
				if (++edgeCnt == _v - 1) {
					return totalWeight;
				}
			}
		}

		return -1;
	}

	private static void union(int x, int y) {
		_p[find(y)] = find(x);
	}

	private static int find(int x) {
		if (_p[x] == x) {
			return x;
		} else {
			return _p[x] = find(_p[x]);
		}
	}

	private static void make() {
		_p = new int[_v + 1];
		for (int i = 1; i <= _v; i++) {
			_p[i] = i;
		}
	}

	private static long prim() {
		long totalCost = 0;

		boolean[] visited = new boolean[_v + 1];
		PriorityQueue<Node> pq = new PriorityQueue<>();

		pq.offer(new Node(1, 0));

		int cnt = 0;
		while (!pq.isEmpty()) {
			Node minCostHead = pq.poll();

			if (visited[minCostHead.no]) {
				continue;
			}
			visited[minCostHead.no] = true;
			totalCost += minCostHead.weight;
			if (++cnt == _v) {
				return totalCost;
			}
			
			for (Node n : _nodes[minCostHead.no]) {
				if (!visited[n.no]) {
					pq.offer(n);
				}
			}
		}

		return -1;

	}

}
