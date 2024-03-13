import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _e, _v1, _v2;

	static List<Node>[] _graph;

	static int dijkstra(int start, int dest) {
		int[] md = new int[_n + 1]; // minimum distance
		Arrays.fill(md, Integer.MAX_VALUE);
		md[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node polled = pq.poll();

			if (polled.weight > md[polled.idx]) {
				continue;
			}

			if (polled.idx == dest) {
				return polled.weight;
			}

			for (Node n : _graph[polled.idx]) {
				if (polled.weight + n.weight < md[n.idx]) {
					md[n.idx] = polled.weight + n.weight;
					pq.offer(new Node(n.idx, md[n.idx]));
				}
			}
		}

		return -1;
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_e = Integer.parseInt(tokens.nextToken());

		_graph = new List[_n + 1];
		for (int i = 1; i <= _n; i++) {
			_graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < _e; i++) {
			tokens = new StringTokenizer(input.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			int weight = Integer.parseInt(tokens.nextToken());

			_graph[from].add(new Node(to, weight));
			_graph[to].add(new Node(from, weight));
		}

		tokens = new StringTokenizer(input.readLine());
		_v1 = Integer.parseInt(tokens.nextToken());
		_v2 = Integer.parseInt(tokens.nextToken());

		int answer11 = dijkstra(1, _v1);
		int answer12 = dijkstra(_v1, _v2);
		int answer13 = dijkstra(_v2, _n);

		int answer1 = -1;

		if (answer11 != -1 && answer12 != -1 && answer13 != -1) {
			answer1 = answer11 + answer12 + answer13;
		}

		int answer21 = dijkstra(1, _v2);
		int answer22 = dijkstra(_v2, _v1);
		int answer23 = dijkstra(_v1, _n);

		int answer2 = -1;

		if (answer21 != -1 && answer22 != -1 && answer23 != -1) {
			answer2 = answer21 + answer22 + answer23;
		}

		int answer = -1;

		if (answer1 != -1 && answer2 != -1) {
			answer = Math.min(answer1, answer2);
		} else if (answer1 != -1) {
			answer = answer1;
		} else if (answer2 != -1) {
			answer = answer2;
		}

		System.out.println(answer);

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