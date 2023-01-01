package ct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최소정수출력 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		pq = new PriorityQueue<>();
		while (_n-- > 0) {
			int in = Integer.parseInt(input.readLine());
			if (in == 0) {
				if (!pq.isEmpty()) {
					output.append(pq.poll()).append("\n");
				} else {
					output.append("0\n");
				}
			} else {
				pq.add(in);
			}
		}

		System.out.println(output);

	}

}
