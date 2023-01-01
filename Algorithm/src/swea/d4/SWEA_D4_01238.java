package swea.d4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author hh
 * @category #bfs
 * @memo List의 배열을 선언할 때, 'static List<Integer>[] _contacts;' 리스트의 타입을 명시해줘야
 *       Object형이 아닌, 원하는 타입으로 순회할 수 있다.
 *
 */

public class SWEA_D4_01238 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _start, _answer;
	static List<Integer>[] _contacts;

	static void bfs() {
		Queue<Integer> q = new ArrayDeque<Integer>();
		boolean[] isVisited = new boolean[101];

		q.offer(_start);
		isVisited[_start] = true;

		while (!q.isEmpty()) {
			int size = q.size();
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < size; i++) {
				int cur = q.poll();
				max = Math.max(max, cur);
				for (Integer person : _contacts[cur]) {
					if (!isVisited[person]) {
						q.offer(person);
						isVisited[person] = true;
					}
				}
			}
			_answer = max;
		}
	}

	public static void main(String[] args) throws IOException {
//		input = new BufferedReader(new FileReader("./io/swea01238input.txt"));// d

		for (int tc = 1; tc <= 10; tc++) {
			tokens = new StringTokenizer(input.readLine());
			_n = Integer.parseInt(tokens.nextToken()) / 2;
			_start = Integer.parseInt(tokens.nextToken());

			_contacts = new List[101];
			for (int i = 1; i <= 100; i++) {
				_contacts[i] = new ArrayList<Integer>();
			}

			tokens = new StringTokenizer(input.readLine());
			for (int i = 0; i < _n; i++) {
				int from = Integer.parseInt(tokens.nextToken());
				int to = Integer.parseInt(tokens.nextToken());
				_contacts[from].add(to);
			}

			_answer = -1;
			bfs();

			output.append("#").append(tc).append(" ").append(_answer).append("\n");
		}

		System.out.println(output);

	}

}
