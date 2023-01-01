package ct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 정원입장은선착순 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static PriorityQueue<Time> _pq;
	static PriorityQueue<Time2> _waiters;

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_pq = new PriorityQueue<>();
		for (int i = 0; i < _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			_pq.add(new Time(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()), i + 1));
		}

		Time start = _pq.poll();
		int pet = start.a; // previous enter time
		int pit = start.t; // previous in time

		_waiters = new PriorityQueue<>();
		int answer = Integer.MIN_VALUE;
		while (_n-- > 1) {
			Time now = _pq.poll();
			System.out.println("pet:" + pet + ",pit:" + pit + ",now.a" + now.a);
			if (now.a < pet + pit) {
				_waiters.add(new Time2(now.a, now.t, now.num));
				Time2 now2 = _waiters.poll();
				int wt = pet + pit - now2.a; // waiting time
				answer = Math.max(answer, wt);
				pet = pet + pit;
				pit = now2.t;
			} else {
				pet = now.a;
				pit = now.t;
			}
		}

		System.out.println(answer);

	}

	static class Time implements Comparable<Time> {
		int a, t, num;

		public Time(int a, int t, int num) {
			this.a = a;
			this.t = t;
			this.num = num;
		}

		@Override
		public int compareTo(Time o) {
			return Integer.compare(this.a, o.a);
		}

	}

	static class Time2 implements Comparable<Time2> {
		int a, t, num;

		public Time2(int a, int t, int num) {
			this.a = a;
			this.t = t;
			this.num = num;
		}

		@Override
		public int compareTo(Time2 o) {
			return Integer.compare(this.num, o.num);
		}

	}

}
