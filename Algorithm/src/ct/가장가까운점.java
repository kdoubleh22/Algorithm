package ct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 가장가까운점 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m;
	static PriorityQueue<Point> _pq;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());

		_pq = new PriorityQueue<>();
		while (_n-- > 0) {
			tokens = new StringTokenizer(input.readLine());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());

			_pq.add(new Point(x, y));
		}

		while (_m-- > 0) {
			Point polled = _pq.poll();
			_pq.add(new Point(polled.x + 2, polled.y + 2));
		}

		Point answer = _pq.poll();
		System.out.println(answer.x + " " + answer.y);

	}

	static class Point implements Comparable<Point> {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int dist(int a, int b) {
			return Math.abs(a) + Math.abs(b);
		}

		@Override
		public int compareTo(Point o) {
			if (dist(this.x, this.y) == dist(o.x, o.y)) {
				if (this.x == o.x) {
					return Integer.compare(this.y, o.y);
				} else {
					return Integer.compare(this.x, o.x);
				}
			} else {
				return Integer.compare(dist(this.x, this.y), dist(o.x, o.y));
			}
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}

	}

}
