package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author
 * @see https://www.acmicpc.net/problem/1967
 * @performance 
 * @category #
 * @memo 1<=n<=10,000. 배열로 풀면 메모리 초과.
 * 
 */

public class BJ_G4_01967 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _answer;
	static int[][] _tree;

	static void find(int current, int distance, boolean[] isVisited) {
		// 방문처리
		isVisited[current] = true;

		if (distance > _answer) {
			_answer = distance;
		}

		// 자식이 있으면,
		for (int i = 1; i <= _n; i++) {
			if (_tree[current][i] != 0 && !isVisited[i]) {
				find(i, distance + _tree[current][i], isVisited);
			}
		}

		// 부모가 있으면,
		for (int i = 1; i <= _n; i++) {
			if (_tree[i][current] != 0 && !isVisited[i]) {
				find(i, distance + _tree[i][current], isVisited);
			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_tree = new int[_n + 1][_n + 1];
		for (int i = 0; i < _n - 1; i++) {
			tokens = new StringTokenizer(input.readLine());
			int parent = Integer.parseInt(tokens.nextToken());
			int child = Integer.parseInt(tokens.nextToken());
			int weight = Integer.parseInt(tokens.nextToken());
			_tree[parent][child] = weight;
		}

		_answer = Integer.MIN_VALUE;
		find(_n, 0, new boolean[_n + 1]);

		output.append(_answer);
		System.out.print(output);
	}

}
