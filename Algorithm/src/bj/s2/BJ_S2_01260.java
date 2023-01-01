package bj.s2;

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

/**
 * 
 * @author kdoubleh22
 * @see https://www.acmicpc.net/problem/1260
 * @performance 18156 KB 192 ms
 * @category #그래프 이론 #그래프 탐색 #너비 우선 탐색 #깊이 우선 탐색
 * @memo 방향이 없는 간선은 양쪽에 넣기.배열에 list를 담을 수 있다. list deepcopy 가능한지 찾아보기. list도 for
 *       each 쓸 수 있음. bfs 큐는 담을 때 방문처리해야 깔끔한데, bfs2도 알아둬야됨. 왜냐하면 프림 알고리즘에서 pq를
 *       쓰면 방문처리를 poll하고 해야됨.
 * 
 */

public class BJ_S2_01260 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m, _v;
	static List<Integer>[] _edges;
	static boolean[] _dfsVisited, _bfsVisited;

	static void bfs(int start) {
		// 1. 준비물
		Queue<Integer> q = new LinkedList<>();
		_bfsVisited[start] = true;

		// 2. 초기화작업
		q.offer(start);

		while (!q.isEmpty()) {
			// 3. 대장 데려오기.
			int polled = q.poll();

			// 4. 할일 처리.
			output.append(polled).append(" ");
			for (int cur : _edges[polled]) {
				if (!_bfsVisited[cur]) {
					q.offer(cur);
					_bfsVisited[cur] = true;
				}
			}
		}
	}

	static void bfs2(int start) { // 정석
		// 1. 준비물
		Queue<Integer> q = new LinkedList<>();

		// 2. 초기화작업
		q.offer(start);

		while (!q.isEmpty()) {
			// 3. 대장 데려오기.
			int polled = q.poll();
			if (_bfsVisited[polled]) {
				continue;
			}

			_bfsVisited[polled] = true;

			// 4. 할일 처리.
			output.append(polled).append(" ");
			for (int cur : _edges[polled]) {
				if (!_bfsVisited[cur]) {
					q.offer(cur);
				}
			}
		}
	}

	static void dfs(int now) {
		// 1. 할일처리.
		_dfsVisited[now] = true;
		output.append(now).append(" ");

		// 2. 자식 탐색
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
		bfs2(_v);

		System.out.println(output);
	}

}
