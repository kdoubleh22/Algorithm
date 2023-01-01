package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G5_01107 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m, _answer;
	static int[] _arr;

	static void bfs() {
		boolean[] isVisited = new boolean[_n + 1];
		Queue<Integer> q = new LinkedList<>();

		q.offer(100);
		isVisited[100] = true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());
		List<Integer> list = new LinkedList<>();
		for (int i = -2; i < 10; i++) { // -2는 + -1은 -
			list.add(i);
		}

		_m = Integer.parseInt(input.readLine());

		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < _m; i++) {
			list.remove(list.indexOf(Integer.parseInt(tokens.nextToken())));
		}

		_arr = new int[list.size()];
		int idx = 0;
		for (int i : list) {
			_arr[idx++] = i;
		}

		_answer = 0;
		bfs();

		System.out.println(_answer);

	}

}
