package ct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 큰숫자만계속고르기 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m;
	static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());

		pq = new PriorityQueue<>(Collections.reverseOrder());

		tokens = new StringTokenizer(input.readLine());
		while (_n-- > 0) {
			pq.add(Integer.parseInt(tokens.nextToken()));
		}

		while (_m-- > 0) {
			pq.add(pq.poll() - 1);
		}

		System.out.println(pq.poll());
	}

}
