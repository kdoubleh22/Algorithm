package bj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * PQ에 넣고, 연산횟수를 기준으로 빼서 완탐
 */

public class BJ_S3_01463 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static PriorityQueue<NC> _pq;

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine()); // 1 <= n <= 10^6

		_pq = new PriorityQueue<>();
		boolean[] isVisited = new boolean[1000001];

		int answer = 0;

		_pq.add(new NC(_n, 0)); // 초기화
		isVisited[_n] = true;

		while (!_pq.isEmpty()) {
			NC now = _pq.poll();

			if (now.n == 1) {
				answer = now.c;
				break;
			}

			if (now.n % 3 == 0 && !isVisited[now.n % 3]) {
				_pq.add(new NC(now.n / 3, now.c + 1));
				isVisited[now.n / 3] = true;
			}
			if (now.n % 2 == 0 && !isVisited[now.n % 2]) {
				_pq.add(new NC(now.n / 2, now.c + 1));
				isVisited[now.n / 2] = true;
			}
			if (!isVisited[now.n - 1]) {
				_pq.add(new NC(now.n - 1, now.c + 1));
				isVisited[now.n - 1] = true;
			}
		}

		System.out.println(answer);

	}

	static class NC implements Comparable<NC> { // Number, Count
		int n;
		int c;

		public NC(int n, int c) {
			this.n = n;
			this.c = c;
		}

		@Override
		public int compareTo(NC o) {
			return Integer.compare(this.c, o.c);
		}

	}

}
