package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G4_02239 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] _puzzle;
	static boolean[][] _rowCheck, _colCheck;
	static boolean[][][] _boxCheck;
	static boolean _isDone;

	static int[] findBox(int row, int col) {
		int[] result = new int[2];
		row = (row - 1) / 3;
		col = (col - 1) / 3;
		result[0] = row;
		result[1] = col;
		return result;
	}

	static void recur(int row, int col) {
		if (row == 10 && col == 1) {
			_isDone = true;
			return;
		}

		int nr = row;
		int nc = col + 1;
		if (nc == 10) {
			nr += 1;
			nc = 1;
		}

		if (_puzzle[row][col] != 0) {
			recur(nr, nc);
		} else {
			int[] result = findBox(row, col);
			for (int i = 1; i <= 9; i++) {
				if (!_rowCheck[row][i] && !_colCheck[col][i] && !_boxCheck[result[0]][result[1]][i]) {
					_puzzle[row][col] = i;
					_rowCheck[row][i] = true;
					_colCheck[col][i] = true;
					_boxCheck[result[0]][result[1]][i] = true;
					recur(nr, nc);
					if (_isDone) {
						return;
					}
					_puzzle[row][col] = 0;
					_rowCheck[row][i] = false;
					_colCheck[col][i] = false;
					_boxCheck[result[0]][result[1]][i] = false;
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		_puzzle = new int[10][10];
		_rowCheck = new boolean[10][10];
		_colCheck = new boolean[10][10];
		_boxCheck = new boolean[4][4][10];

		for (int i = 1; i <= 9; i++) {
			char[] line = input.readLine().toCharArray();
			for (int j = 1; j <= 9; j++) {
				_puzzle[i][j] = line[j - 1] - '0';
				_rowCheck[i][_puzzle[i][j]] = true;
				_colCheck[j][_puzzle[i][j]] = true;
				int[] result = findBox(i, j);
				_boxCheck[result[0]][result[1]][_puzzle[i][j]] = true;
			}

		} // 입력 끝.

		_isDone = false;
		recur(1, 1);

		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				output.append(_puzzle[i][j]);
			}
			output.append("\n");
		}

		System.out.println(output);

	}

}
