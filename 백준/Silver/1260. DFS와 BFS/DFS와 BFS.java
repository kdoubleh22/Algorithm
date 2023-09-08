import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m, _v;
	static List<Integer>[] _edges;
	static boolean[] _dfsVisited, _bfsVisited;

	static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		_bfsVisited[start] = true;
		while (!q.isEmpty()) {
			int polled = q.poll();
			output.append(polled).append(" ");
			for (int cur : _edges[polled]) {
				if (!_bfsVisited[cur]) {
					q.offer(cur);
					_bfsVisited[cur] = true;
				}
			}
		}
	}

	static void dfs(int now) {
		// 방문처리.
		_dfsVisited[now] = true;
		output.append(now).append(" ");
		for (int cur : _edges[now]) {
			if (!_dfsVisited[cur]) {
				dfs(cur);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());
		_v = Integer.parseInt(tokens.nextToken());

		_edges = new LinkedList[_n + 1];
		for (int i = 1; i <= _n; i++) {
			_edges[i] = new LinkedList<>();
		}

		for (int i = 0; i < _m; i++) {
			tokens = new StringTokenizer(input.readLine());
			int left = Integer.parseInt(tokens.nextToken());
			int right = Integer.parseInt(tokens.nextToken());
			_edges[left].add(right);
			_edges[right].add(left);
		}

		for (int i = 1; i <= _n; i++) {
			if (!_edges[i].isEmpty()) {
				Collections.sort(_edges[i]);
			}
		}

//		for (int i = 1; i <= _n; i++) {
//			System.out.println(Arrays.toString(_edges[i].toArray()));
//		}

		_dfsVisited = new boolean[_n + 1];
		dfs(_v);
		output.append("\n");

		_bfsVisited = new boolean[_n + 1];
		bfs(_v);

		System.out.println(output);
	}

}