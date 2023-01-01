package bj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_S1_01697 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _k, _answer;

	static boolean isIn(int n) {
		return n >= 0 && n <= 100000;
	}

	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		boolean[] isVisited = new boolean[100001];
		q.offer(_n);
		isVisited[_n] = true;

		int time = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int polled = q.poll();

				if (polled == _k) {
					_answer = time;
					return;
				}
				if (polled > _k) {
					_answer += time + (polled - _k);
				}
				if (isIn(polled - 1) && !isVisited[polled - 1]) {
					q.offer(polled - 1);
					isVisited[polled - 1] = true;
				}
				if (isIn(polled + 1) && !isVisited[polled + 1]) {
					q.offer(polled + 1);
					isVisited[polled + 1] = true;
				}
				if (isIn(polled * 2) && !isVisited[polled * 2]) {
					if (_k > 2 * polled - 2) {
						q.offer(polled * 2);
						isVisited[polled * 2] = true;
					}
				}
			}
			time++;
		}
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_k = Integer.parseInt(tokens.nextToken());

		_answer = -1;
		bfs();

		output.append(_answer);
		System.out.println(output);

	}

}
