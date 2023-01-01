package swea.nd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author hh
 * @time 14:27 ~ 16:25
 */

public class SWEA_ND_15173 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _answer;
	static int[] _deck;
	static boolean _finded;

	static boolean sortedU(int[] deck) {
		for (int i = 0; i < _n; i++) {
			if (deck[i] != i + 1) {
				return false;
			}
		}
		return true;
	}

	static boolean sortedD(int[] deck) {
		for (int i = 0; i < _n; i++) {
			if (deck[i] != _n - i) {
				return false;
			}
		}
		return true;
	}

	private static int[] shuffle(int[] deck, int dial) {

		int[] result = new int[_n];
		int size = _n / 2;

		int firstIdx = -1;
		int secondIdx = -1;
		if (dial < _n / 2) {
			firstIdx = 0;
			secondIdx = _n / 2;
		} else {
			dial = _n - 1 - dial; // 대칭 시켜줌. ex) 5->0, 4->1, 3->2
			firstIdx = _n / 2;
			secondIdx = 0;
		}

		int idx = 0;
		for (int i = 0; i < size - dial; i++) {
			result[idx++] = deck[firstIdx++];
		}

		for (int i = 0; i < dial; i++) {
			result[idx++] = deck[secondIdx++];
			result[idx++] = deck[firstIdx++];
		}

		for (int i = 0; i < size - dial; i++) {
			result[idx++] = deck[secondIdx++];
		}

		return result;
	}

	static void dupPerm(int cnt, int[] deck, int shuffleNum) {

		if (cnt == shuffleNum) {
			if (sortedU(deck) || sortedD(deck)) {
				_answer = cnt;
				_finded = true;
			}
			return;
		}

		for (int i = 0; i < _n; i++) {
			
			int[] result = shuffle(deck, i);

			dupPerm(cnt + 1, result, shuffleNum);

			if (_finded) {
				return;
			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {

//		input = new BufferedReader(new FileReader("./io/swea15173input.txt"));

		int t = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= t; tc++) {

			_n = Integer.parseInt(input.readLine());

			_deck = new int[_n];
			tokens = new StringTokenizer(input.readLine());
			for (int i = 0; i < _n; i++) {
				_deck[i] = Integer.parseInt(tokens.nextToken());
			} // 입력.

			_answer = -1;
			_finded = false;
			for (int i = 0; i <= 5; i++) {
				dupPerm(0, _deck, i);
				if (_finded) {
					break;
				}
			}

			output.append("#").append(tc).append(" ").append(_answer).append("\n");

		} // tc

		System.out.println(output);

	}

}
