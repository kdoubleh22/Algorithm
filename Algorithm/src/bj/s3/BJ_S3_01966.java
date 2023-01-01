package bj.s3;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_S3_01966 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _t, _n, _idx;
	static Queue<Point> q; // x는 인덱스, y는 priority
	static List<Integer> list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		_t = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= _t; tc++) {
			tokens = new StringTokenizer(input.readLine());
			_n = Integer.parseInt(tokens.nextToken());
			_idx = Integer.parseInt(tokens.nextToken());

			tokens = new StringTokenizer(input.readLine());
			q = new LinkedList<Point>();
			list = new ArrayList<Integer>();
			for (int i = 0; i < _n; i++) {
				int priority = Integer.parseInt(tokens.nextToken());
				q.add(new Point(i, priority));
				list.add(priority);
			}

			Collections.sort(list, Collections.reverseOrder());

			int cnt = 1;
			while (!q.isEmpty()) {
				int cur = q.peek().y;
				if (cur == list.get(0)) {
					if (q.peek().x == _idx) {
						output.append(cnt).append("\n");
						break;
					} else {
						q.poll();
						list.remove(0);
						cnt++;
					}
				} else {
					q.offer(q.poll());
				}
			}

		}

		System.out.println(output);

	}

}
