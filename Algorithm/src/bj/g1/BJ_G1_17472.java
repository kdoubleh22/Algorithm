package bj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G1_17472 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int _n, _m, _islandNum, _answer, _sum;
	static int[][] _map;
	static boolean[][] _isVisited;
	static List<Point> _islands;
	static List<Node>[] _graph;

	static boolean isIn(int r, int c) {
		return 0 <= r && r < _n && 0 <= c && c < _m;
	}

	static void bfs(int r, int c) {
		_islandNum++;

		Queue<Point> q = new ArrayDeque<>();

		q.offer(new Point(r, c));
		_isVisited[r][c] = true;

		while (!q.isEmpty()) {
			Point cur = q.poll();

			// 맵에 섬 표시
			_map[cur.r][cur.c] = _islandNum;

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];

				if (isIn(nr, nc) && _map[nr][nc] == 1 && !_isVisited[nr][nc]) {
					q.offer(new Point(nr, nc));
					_isVisited[nr][nc] = true;
				}
			}

			
			
		}

	}

	static void makeBridge(int sr, int sc) {
		Queue<Point> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[_n][_m];

		q.offer(new Point(sr, sc));
		visited[sr][sc] = true;

		int islandIdx = _map[sr][sc];

		int[] minDist = new int[_islands.size() + 1];
		Arrays.fill(minDist, Integer.MAX_VALUE);

		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];
				// 섬 탐색
				if (isIn(nr, nc) && _map[nr][nc] == islandIdx && !visited[nr][nc]) {
					q.offer(new Point(nr, nc));
					visited[nr][nc] = true;
				}
				// 다리 건설가능한지
				if (isIn(nr, nc)) { // 가능한 칸이면 쭉 가본다
					int length = 0;
//					boolean isFound = false;
					while (isIn(nr, nc)) {
						length++;

						if (_map[nr][nc] != 0) { // 섬인데
							if (_map[nr][nc] != islandIdx && length >= 3) { // 내 섬이 아니면 다리놓기가능
//								isFound = true;
								minDist[_map[nr][nc]] = Math.min(minDist[_map[nr][nc]], length - 1);
								break;
							} else { // 자기 자신이면,
								break;
							}
						}

						nr += deltas[d][0];
						nc += deltas[d][1];
					}
				}

			}
		}

		for (int i = 1; i <= _islands.size(); i++) {
			if (minDist[i] != Integer.MAX_VALUE) {
				_graph[islandIdx].add(new Node(i, minDist[i]));
			}
		}

	}

	static void prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[_islands.size() + 1];

		pq.offer(new Node(1, 0));

		int nodeCnt = 0;
		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (visited[cur.idx]) {
				continue;
			}

			visited[cur.idx] = true;

			_sum += cur.cost;

			if (++nodeCnt == _islands.size()) {
				_answer = _sum;
			}

//			System.out.println("cur.idx:" + cur.idx);
			for (Node n : _graph[cur.idx]) {
				if (!visited[n.idx]) {
					pq.add(new Node(n.idx, n.cost));
				}
			}

		}
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());

		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());

		_map = new int[_n][_m];
		for (int i = 0; i < _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < _m; j++) {
				_map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}

		// 섬 구하기
		_isVisited = new boolean[_n][_m];
		_islandNum = 0;
		_islands = new ArrayList<>(); // _islands의 인덱스+1이 섬 인덱스
		for (int i = 0; i < _n; i++) {
			for (int j = 0; j < _m; j++) {
				if (_map[i][j] == 1 && !_isVisited[i][j]) {
					_islands.add(new Point(i, j));
					bfs(i, j);
				}
			}
		}

//		for (int[] row : _map) {
//			System.out.println(Arrays.toString(row));
//		}
//		System.out.println(_islands);
		// 다리 만들어보기
		_graph = new List[_islands.size() + 1];
		for (int i = 1; i <= _islands.size(); i++) {
			_graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < _islands.size(); i++) {
			makeBridge(_islands.get(i).r, _islands.get(i).c);
		}

//		for (List<Node> l : _graph) {
//			System.out.println(l);
//		}

		// MST Prim
		_answer = -1;
		_sum = 0;
		prim();

		System.out.println(_answer);

	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}

	}

	static class Node implements Comparable<Node> {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Node [idx=" + idx + ", cost=" + cost + "]";
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}

	}
}
