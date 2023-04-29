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

	static int _v, _e, _s; // _s:start

	static List<Vertex>[] _graph;

	static void dijkstra() {
		int[] md = new int[_v + 1];
		Arrays.fill(md, Integer.MAX_VALUE);
		md[_s] = 0;

		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(_s, 0));

		while (!pq.isEmpty()) {
			Vertex polled = pq.poll();

			if (polled.weight > md[polled.to]) {
				continue;
			}

			for (Vertex v : _graph[polled.to]) {
				if (polled.weight + v.weight < md[v.to]) {
					md[v.to] = polled.weight + v.weight;
					pq.offer(new Vertex(v.to, md[v.to]));
				}
			}
		}

		for (int i = 1; i <= _v; i++) {
			if (md[i] != Integer.MAX_VALUE) {
				output.append(md[i]);
			} else {
				output.append("INF");
			}
			output.append("\n");
		}

	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_v = Integer.parseInt(tokens.nextToken());
		_e = Integer.parseInt(tokens.nextToken());
		_s = Integer.parseInt(input.readLine());

		_graph = new List[_v + 1];
		for (int i = 0; i <= _v; i++) {
			_graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < _e; i++) {
			tokens = new StringTokenizer(input.readLine());
			int u = Integer.parseInt(tokens.nextToken());
			int v = Integer.parseInt(tokens.nextToken());
			int w = Integer.parseInt(tokens.nextToken());

			_graph[u].add(new Vertex(v, w));
		}

		dijkstra();

		System.out.println(output);

	}

	static class Vertex implements Comparable<Vertex> {
		int to, weight;

		public Vertex(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.weight, o.weight);
		}

	}

}