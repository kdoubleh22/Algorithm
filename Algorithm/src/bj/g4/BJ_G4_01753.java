package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G4_01753 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _answer;
	static int[][] _costs;

	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_costs = new int[_n][_n];
		for (int i = 0; i < _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < _n; j++) {
				_costs[i][j] = Integer.parseInt(tokens.nextToken());
			}
		} // 입력

		_answer = -1;
		dijkstra();

		output.append(_answer);
		System.out.println(output);
	}

	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] aws = new int[_n];

		Arrays.fill(aws, Integer.MAX_VALUE); // 거리 무한대로 초기화.

		// 0번 출발
		aws[0] = 0;
		pq.offer(new Node(0, 0));

		int cnt = 0;
		while (!pq.isEmpty()) {
			Node head = pq.poll();
			System.out.println(Arrays.toString(aws));
			System.out.println("head.n:"+head.n+",head.aw:"+head.aw);
			cnt++;

			if (cnt == _n) {
				int min = Integer.MAX_VALUE;
				for (int i = 1; i < _n; i++) {
					min = Math.min(min, _costs[i][0]);
				}
				_answer = head.aw + min;
				return;
			}

			for (int i = 0; i < _n; i++) {
				if (aws[i] > aws[head.n] + _costs[head.n][i]) {
					aws[i] = aws[head.n] + _costs[head.n][i];
					pq.offer(new Node(i, aws[i]));
				}
			}

		}

	}

	static class Node implements Comparable<Node> {
		int n, aw; // accumulated weight;

		public Node(int n, int aw) {
			this.n = n;
			this.aw = aw;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.aw, o.aw);
		}

	}

}
