package bj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author kdoubleh22
 * @see https://www.acmicpc.net/problem/16926
 * @performance 35920 KB 408 ms
 * @category #
 * @memo 변수 이름 정할 때 안 겹치게 하기. 웬만하면 전역변수는 대문자로 쓰는게 좋을수도 있겠다. 이번 문제의 경우에선 몇 바퀴
 *       돌려야할지에 대한 r과 row의 r이 겹쳐서 후자를 rr로 바꿨더니 통과했음. 운 좋게 찾았지만, 변수명의 디버깅은 쉽지
 *       않으므로, 처음부터 조심하자!
 */

public class BJ_S1_16926 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] deltas = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } }; // 아래 오른쪽 위쪽 왼쪽 ccw

	static int n, m, r;
	static int[][] A;
	static boolean[][] isRotated;

	static boolean isIn(int r, int c) {
		return r >= 1 && r < n + 1 && c >= 1 && c < m + 1;
	}

	static void rotate() {
		int x = 1;
		int y = 1;
		while (!isRotated[x][y]) {

//			System.out.println("in" + x + y);//d
			int rr = x;
			int c = y;
			Deque<Integer> q = new ArrayDeque<>();
			q.offer(A[rr][c]);
			int deltasIdx = 0;
			// 큐에 넣기.
			while (!(rr == x && c == y + 1)) {
				int nr = rr + deltas[deltasIdx][0];
				int nc = c + deltas[deltasIdx][1];
				if (isIn(nr, nc) && !isRotated[nr][nc]) {
					q.offer(A[nr][nc]);
					rr = nr;
					c = nc;
				} else { // 배열 밖이거나 이미 돌았던 곳이면, deltasIdx update.
					deltasIdx += 1;
				}
			}
			// 큐 rotate.
			for (int i = 0; i < r; i++) {
				q.offerFirst(q.pollLast());
			}
			// 다시 배열에 옮기기.
			rr = x;
			c = y;
			A[rr][c] = q.poll();
			isRotated[rr][c] = true;
			deltasIdx = 0;
			while (true) {
				int nr = rr + deltas[deltasIdx][0];
				int nc = c + deltas[deltasIdx][1];
				if (nr == x && nc == y) {
					break;
				} else {
					if (isIn(nr, nc) && !isRotated[nr][nc]) {
						rr = nr;
						c = nc;
						A[rr][c] = q.poll();
						isRotated[rr][c] = true;
					} else { // 배열 밖이거나 이미 돌았던 곳이면, deltasIdx update.
						deltasIdx += 1;
					}
				}
			}

//			for (int i = 0; i < n + 1; i++) {//d
//				System.out.println(Arrays.toString(isRotated[i]));
//			}

			x++;
			y++;

		}
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		n = Integer.parseInt(tokens.nextToken());
		m = Integer.parseInt(tokens.nextToken());
		r = Integer.parseInt(tokens.nextToken());

		A = new int[n + 1][m + 1];
		for (int i = 1; i < A.length; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 1; j < A[0].length; j++) {
				A[i][j] = Integer.parseInt(tokens.nextToken());
			}
		} // 입력 끝.

		// 입력 확인.
//		for (int i = 0; i < arr.length; i++) {
//			for (int j = 0; j < arr[0].length; j++) {
//				System.out.printf("%d ",arr[i][j]);
//			}
//			System.out.println();
//		}

		isRotated = new boolean[n + 1][m + 1];
		rotate();

		for (int i = 1; i < A.length; i++) {
			for (int j = 1; j < A[0].length; j++) {
				output.append(A[i][j]).append(" ");
			}
			output.append("\n");
		}

		System.out.print(output);

	}

}
