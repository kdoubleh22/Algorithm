package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_G5_13549 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _k, _answer;

	static boolean isIn(int n) {
		return 0 <= n && n < 200000;
	}

	static void zobfs() { // zero one bfs
		Deque<Point> dq = new ArrayDeque<>();
		boolean[] isVisited = new boolean[200001];

		dq.offer(new Point(0, _n));
		isVisited[_n] = true;

		while (!dq.isEmpty()) {
			Point cur = dq.poll();
			int t = cur.t;
			int loc = cur.loc;

			if (loc == _k) {
				_answer = t;
				return;
			}

			if (isIn(loc * 2) && !isVisited[loc * 2]) {
				dq.offerFirst(new Point(t, loc * 2));
				isVisited[loc * 2] = true;
			}

			if (isIn(loc + 1) && !isVisited[loc + 1]) {
				dq.offerLast(new Point(t + 1, loc + 1));
				isVisited[loc + 1] = true;
			}

			if (isIn(loc - 1) && !isVisited[loc - 1]) {
				dq.offerLast(new Point(t + 1, loc - 1));
				isVisited[loc - 1] = true;
			}

		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_k = Integer.parseInt(tokens.nextToken());

		_answer = 0;
		zobfs();

		System.out.println(_answer);
	}

	static class Point {
		int t, loc; // time, location

		public Point(int t, int loc) {
			this.t = t;
			this.loc = loc;
		}

	}

}
