package bj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public abstract class BJ_S2_3085 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static char[][] _board;
	static char[] _colors = { 'C', 'P', 'Z', 'Y' };

	static int findRowMax() {
		int max = Integer.MIN_VALUE;
		for (int r = 0; r < _n; r++) {
			char prev = _board[r][0];
			int conti = 1;
			for (int c = 1; c < _n; c++) {
				if (prev == _board[r][c]) {
					conti += 1;
					max = Math.max(max, conti);
				} else {
					conti = 0;
				}
			}
		}
		return max;
	}

	static int findColMax() {
		int max = Integer.MIN_VALUE;
		for (int c = 0; c < _n; c++) {
			char prev = _board[c][0];
			int conti = 1;
			for (int r = 1; r < _n; r++) {
				if (prev == _board[r][c]) {
					conti += 1;
					max = Math.max(max, conti);
				} else {
					conti = 0;
				}
			}
		}
		return max;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_board = new char[_n][];
		for (int i = 0; i < _n; i++) {
			_board[i] = input.readLine().toCharArray();
		}

		int answer = Integer.MIN_VALUE;
		// 오른쪽이랑 교환
		for (int r = 0; r < _n; r++) {
			for (int c = 0; c < _n - 1; c++) {
				if (_board[r][c] != _board[r][c + 1]) {
					char temp = _board[r][c];
					_board[r][c] = _board[r][c + 1];
					_board[r][c + 1] = temp;
						answer = Math.max(answer, findRowMax());
						answer = Math.max(answer, findColMax());
					_board[r][c + 1] = _board[r][c];
					_board[r][c] = temp;
				}
			}
		}

		// 아래랑 교환
		for (int r = 0; r < _n - 1; r++) {
			for (int c = 0; c < _n; c++) {
				if (_board[r][c] != _board[r + 1][c]) {
					char temp = _board[r][c];
					_board[r][c] = _board[r + 1][c];
					_board[r + 1][c] = temp;
						answer = Math.max(answer, findRowMax());
						answer = Math.max(answer, findColMax());
					_board[r + 1][c] = _board[r][c];
					_board[r][c] = temp;
				}
			}
		}
		System.out.println(answer);

	}

}
