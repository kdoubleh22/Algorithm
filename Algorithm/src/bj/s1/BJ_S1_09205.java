package bj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_S1_09205 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _t, _n;
	static Point _house, _dest;
	static Point[] _cs; // convenience store

	public static void main(String[] args) throws NumberFormatException, IOException {
		_t = Integer.parseInt(input.readLine());

		while (_t-- > 0) {
			_n = Integer.parseInt(input.readLine());

			tokens = new StringTokenizer(input.readLine());
			_house = new Point(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));

			_cs = new Point[_n];
			for (int i = 0; i < _n; i++) {
				tokens = new StringTokenizer(input.readLine());
				_cs[i] = new Point(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
			}

			tokens = new StringTokenizer(input.readLine());
			_dest = new Point(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));

			bfs();

		}

		System.out.println(output);

	}

	private static void bfs() {
		Queue<Point> q = new ArrayDeque<>();
		boolean[] isVisited = new boolean[_n];

		q.offer(_house);

		while (!q.isEmpty()) {
			Point cur = q.poll();

			if (calDist(cur.x, cur.y, _dest.x, _dest.y) <= 1000) {
				output.append("happy").append("\n");
				return;
			}

			for (int i = 0; i < _n; i++) {
				if (!isVisited[i] && calDist(cur.x, cur.y, _cs[i].x, _cs[i].y) <= 1000) {
					q.offer(_cs[i]);
					isVisited[i] = true;
				}
			}
		}

		output.append("sad").append("\n");

	}

	private static int calDist(int x, int y, int x2, int y2) {
		return Math.abs(x - x2) + Math.abs(y - y2);
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

}
