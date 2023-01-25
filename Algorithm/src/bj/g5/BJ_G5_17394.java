package bj.g5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * -문제
 * 
 * -풀이
 * A이상 B이하의 소수를 구하고, 없으면 -1, 있으면 우선순위큐 bfs.
 */

public class BJ_G5_17394 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _t; // 1 <= T <= 10
	static int _n; // 2 <= N <= 1,000,000
	static int _a, _b; // 2 <= A <= B <= 100,000
	static Set<Integer> _set; // 소수 담는 set.

	static boolean isPrimeNumber(int number) {
		boolean result = true;

		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0) {
				return false;
			}
		}

		return result;
	}

	static int bfs() {
		int result = -2;

		PriorityQueue<Point> pq = new PriorityQueue<>();
		int bigger = Math.max(_n, _b);
		boolean[] visited = new boolean[3 * bigger + 1];
		pq.add(new Point(_n, 0));
		visited[_n] = true;

		while (!pq.isEmpty()) {
			Point cur = pq.poll();

//			System.out.println("cur.num:"+cur.num);
			// 종료 조건
			if (_set.contains(cur.num)) {
				result = cur.cnt;
				break;
			}

			if (!visited[cur.num / 2]) {
				pq.add(new Point(cur.num / 2, cur.cnt + 1));
				visited[cur.num / 2] = true;
			}
			if (!visited[cur.num / 3]) {
				pq.add(new Point(cur.num / 3, cur.cnt + 1));
				visited[cur.num / 3] = true;
			}
			if (!visited[cur.num + 1]) {
				pq.add(new Point(cur.num + 1, cur.cnt + 1));
				visited[cur.num + 1] = true;
			}
			if (cur.num != 0) {
				if (!visited[cur.num - 1]) {
					pq.add(new Point(cur.num - 1, cur.cnt + 1));
					visited[cur.num - 1] = true;
				}
			}
		}

		return result;
	}

	public static void main(String[] args) throws Exception {
		_t = Integer.parseInt(input.readLine());

		for (int t = 0; t < _t; t++) {
			tokens = new StringTokenizer(input.readLine());
			_n = Integer.parseInt(tokens.nextToken());
			_a = Integer.parseInt(tokens.nextToken());
			_b = Integer.parseInt(tokens.nextToken());

			// 소수 구하기
			_set = new HashSet<>();

			for (int i = _a; i < _b + 1; i++) {
				if (isPrimeNumber(i)) {
					_set.add(i);
				}
			}

//			System.out.println(_set);

			// bfs
			if (_set.isEmpty()) { // 소수가 없다면,
				output.append(-1).append("\n");
			} else {
				output.append(bfs()).append("\n");
			}
		}

		System.out.println(output);
	}

	static class Point implements Comparable<Point> {
		int num; // 현재 숫자
		int cnt; // 핑거 스냅 수

		public Point(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.cnt, o.cnt);
		}

	}

}
