package bj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 
 * @author me
 * @see https://www.acmicpc.net/problem/2164
 * @performance 23792 KB	128 ms
 * @category #자료구조 #큐
 * @memo deque는 깡패다. offer, poll 양방향 가능하고, 스택 큐 모두 활용가능.
 * 
 */

public class BJ_S4_02164 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();

	static Deque<Integer> q;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int n = Integer.parseInt(input.readLine());
		q = new ArrayDeque<>();
		for (int i = n; i > 0; i--) {
			q.offerLast(i);
		}
		while (q.size() > 1) {
			q.pollLast();
			q.offerFirst(q.pollLast());
		}
		output.append(q.peek());
		System.out.print(output);
	}

}
