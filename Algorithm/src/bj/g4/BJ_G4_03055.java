package bj.g4;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @memo 한 큐에 넣어도 풀 수 있다.
 *
 */

public class BJ_G4_03055 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static int _r, _c, _answer;
	static char[][] _map;
	static Queue<Point> _waters, _hedgehogs;

	static boolean isIn(int r, int c) {
		return r >= 0 && r < _r && c >= 0 && c < _c;
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_r = Integer.parseInt(tokens.nextToken());
		_c = Integer.parseInt(tokens.nextToken());

		_map = new char[_r][];
		_waters = new LinkedList<>();
		_hedgehogs = new LinkedList<>();
		for (int i = 0; i < _r; i++) {
			_map[i] = input.readLine().toCharArray();
			for (int j = 0; j < _c; j++) {
				if (_map[i][j] == 'S') {
					_hedgehogs.offer(new Point(i, j));
				} else if (_map[i][j] == '*') {
					_waters.offer(new Point(i, j));
				}
			}
		}

		int time = 0;
		_answer = -1;
		boolean[][] isVisited = new boolean[_r][_c];
		outer: while (!_hedgehogs.isEmpty()) {
			// 물의 확장
			int size = _waters.size();
			for (int i = 0; i < size; i++) {
				Point polled = _waters.poll();
				int r = polled.x;
				int c = polled.y;
				for (int j = 0; j < 4; j++) {
					int nr = r + deltas[j][0];
					int nc = c + deltas[j][1];
					if (isIn(nr, nc) && _map[nr][nc] == '.') {
						_map[nr][nc] = '*';
						_waters.offer(new Point(nr, nc));
					}
				}
			}
			// 도치 이동
			size = _hedgehogs.size();
			for (int i = 0; i < size; i++) {
				Point polled = _hedgehogs.poll();
				int r = polled.x;
				int c = polled.y;
				for (int j = 0; j < 4; j++) {
					int nr = r + deltas[j][0];
					int nc = c + deltas[j][1];
					if (isIn(nr, nc)) {
						// 비버집 도착
						if (_map[nr][nc] == 'D') {
							_answer = ++time;
							break outer;
						}
						// 도치 이동
						if (_map[nr][nc] == '.' && !isVisited[nr][nc]) {
							_map[nr][nc] = '*';
							_hedgehogs.offer(new Point(nr, nc));
							isVisited[nr][nc] = true;
						}
					}
				}
			}
			time++;
		}

		if (_answer != -1) {
			output.append(_answer);
		} else {
			output.append("KAKTUS");
		}

		System.out.println(output);

	}

}
