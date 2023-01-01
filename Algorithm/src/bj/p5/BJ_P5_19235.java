package bj.p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_P5_19235 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _score;
	static boolean[][] _green, _blue;

	static void checkColor(boolean[][] board) {
		int cnt = 0; // 몇 줄이 연한 색에 있는지.
		for (int r = 0; r < 2; r++) {
			for (int c = 0; c < 4; c++) {
				if (board[r][c]) {
					cnt += 1;
					break;
				}
			}
		}

		if (cnt == 1) {
			// 마지막줄 클리어
			clearLine(board[5]);
			boolean[] temp = board[5]; // 템프에 클리어 라인 넣어 놓고,
			// 한 줄씩 아래로,
			for (int r = 5; r > 0; r--) {
				board[r] = board[r - 1];
			}
			// 0라인에 temp를.
			board[0] = temp;
		} else if (cnt == 2) {
			// 4,5 번 라인을 클리어
			clearLine(board[4]);
			clearLine(board[5]);

			// temp4, temp5에 각각 넣어둔다.
			boolean[] temp4 = board[4];
			boolean[] temp5 = board[5];

			// 당긴다.
			for (int row = 5; row > 1; row--) {
				board[row] = board[row - 2];
			}

			// clear 했던 temp 4, 5를 0, 1번 라인에 넣어준다.
			board[0] = temp4;
			board[1] = temp5;
		}
	}

	static boolean isLine(boolean[] line) {
		boolean result = true;

		for (int c = 0; c < 4; c++) {
			if (!line[c]) { // 빈 칸이 있으면,
				result = false;
				break;
			}
		}

		return result;
	}

	static void clearLine(boolean[] line) {
		for (int c = 0; c < 4; c++) {
			line[c] = false;
		}
	}

	static void checkLine(boolean[][] board) {
		int row = 5;
		while (row > 1) { // 2, 3, 4, 5줄에 반복.
			if (isLine(board[row])) { // 한 줄이냐?
				// 한 줄 클리어하고,
				clearLine(board[row]);
				// 점수 올리고,
				_score += 1;
				// 위에걸 아래로 당긴다.
				boolean[] temp = board[row]; // temp에다가 현재 clear한 줄을 넣어놓고,
				int tempRow = row;
				// 한 줄 씩 당김.
				while (tempRow > 0) {
					board[tempRow] = board[tempRow - 1];

					tempRow -= 1;
				}
				// 첫번째 줄엔 clear한 걸 넣어 줌.
				board[0] = temp;
				// 한 줄이었으면, 현재 줄도 반복해야하므로, row에 +1을 해줌.
				row += 1;
			}
			row -= 1;
		}
	}

	static void move(boolean[][] board, int t, int x, int y) {
		if (t == 1) {
			int row = 2;
			while (true) {
				if (board[row][y]) { // 블록이 채워져있으면,
					// 한 칸 위로 배치.
					board[row - 1][y] = true;
					break;
				}
				if (row == 5) { // 맨 아래줄인데도, 블록이 비워져있으면,
					// 맨 아래칸에 배치.
					board[row][y] = true;
					break;
				}
				row += 1;
			}
		} else if (t == 2) {
			int row = 2;
			while (true) {
				if (board[row][y] || board[row][y + 1]) { // 현재 칸이 채워져있으면,
					// 한 칸 위로 배치.
					board[row - 1][y] = true;
					board[row - 1][y + 1] = true;
					break;
				}
				if (row == 5) {
					board[row][y] = true;
					board[row][y + 1] = true;
					break;
				}
				row += 1;
			}
		} else { // t == 3
			int row = 2;
			while (true) {
				if (board[row][y]) { // 블록이 채워져있으면,
					board[row - 1][y] = true;
					board[row - 2][y] = true;
					break;
				}
				if (row == 5) { // 맨 아래줄인데도, 블록이 비워져있으면,
					// 맨 아래칸에 배치.
					board[row][y] = true;
					board[row - 1][y] = true;
					break;
				}
				row += 1;
			}
		}
	}

	static void printBoard(boolean[][] board) {
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 4; c++) {
				if (board[r][c]) {
					System.out.print("1 ");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_green = new boolean[6][4];
		_blue = new boolean[6][4]; // 파란색 보드도 돌려서 생각.

		_score = 0;
		while (_n-- > 0) {
			tokens = new StringTokenizer(input.readLine());

			// 1. 블록을 놓는다.
			int t = Integer.parseInt(tokens.nextToken());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());

			// 2. 블록 이동
			move(_green, t, x, y);

			// 기존 열이 새로운 행이 되고, 새로운 열이 3-기존행이 됨.

			if (t == 1) {
				int bx = y; // blue x
				int by = 3 - x; // blue y
				move(_blue, t, bx, by);
			} else if (t == 2) {
				int bx = y; // blue x
				int by = 3 - x; // blue y
				move(_blue, 3, bx, by);
			} else { // t==2
				int bx = y; // blue x
				int by = 3 - x - 1; // blue y
				move(_blue, 2, bx, by);
			}

			// 3. 한 줄 체크
			checkLine(_green);
			checkLine(_blue);

			// 4. 연한 칸에 있는지 체크
			checkColor(_green);
			checkColor(_blue);

//			System.out.println("_n: " + _n);
//			System.out.println("green");
//			printBoard(_green);
//			System.out.println("blue");
//			printBoard(_blue);
		}

		int cnt = 0;
		for (int r = 2; r < 6; r++) {
			for (int c = 0; c < 4; c++) {
				if (_green[r][c]) {
					cnt += 1;
				}
				if (_blue[r][c]) {
					cnt += 1;
				}
			}
		}

		output.append(_score).append("\n");
		output.append(cnt).append("\n");

		System.out.println(output);

	}

}
