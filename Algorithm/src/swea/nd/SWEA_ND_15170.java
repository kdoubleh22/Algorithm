package swea.nd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_ND_15170 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] _gateDeltas = { { 0, 1, 2 }, { 0, 2, 1 }, { 1, 0, 2 }, { 1, 2, 0 }, { 2, 0, 1 }, { 2, 1, 0 } };
	static int[][] _dirDeltas = { { 0, 0, 0 }, { 0, 0, 1 }, { 0, 1, 0 }, { 0, 1, 1 }, { 1, 0, 0 }, { 1, 0, 1 },
			{ 1, 1, 0 }, { 1, 1, 1 } }; // 0: 왼쪽, 1: 오른쪽

	static int _n, _sum, _answer;
	static int[][] _gates; // 0: 게이트 숫자, 1: 몇 명 있는지.
	static boolean[] _entered;

	static boolean isIn(int n) {
		return n >= 1 && n <= _n;
	}

	static void enter(int gateNum, int people, int dir) {
		int idx = 0;

		int[] dirs;
		if (dir == 0) {
			dirs = new int[] { -1, 1 }; // 왼쪽 먼저
		} else {
			dirs = new int[] { 1, -1 }; // 오른쪽 먼저
		}

		for (int p = 0; p < people;) {
			for (int i = 0; i < 2 && p < people; i++) {
				int nIdx = gateNum + idx * dirs[i];
				if (isIn(nIdx) && !_entered[nIdx]) { // 범위 안이고, 방문 안 해봤으면,
					_entered[nIdx] = true;
					p++;
					_sum += idx + 1;
					if (_sum >= _answer) { // 가지치기
						return;
					}
				}
			}
			idx++;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
//		input = new BufferedReader(new FileReader("./io/swea15170input.txt"));

		int t = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= t; tc++) {
			_n = Integer.parseInt(input.readLine());

			_gates = new int[3][2];
			for (int i = 0; i < 3; i++) {
				tokens = new StringTokenizer(input.readLine());
				_gates[i][0] = Integer.parseInt(tokens.nextToken());
				_gates[i][1] = Integer.parseInt(tokens.nextToken());
			} // 입력 끝.

			_answer = Integer.MAX_VALUE;
			for (int g = 0; g < _gateDeltas.length; g++) {
				for (int d = 0; d < 8; d++) {
					_entered = new boolean[_n + 1];
					_sum = 0;
					for (int e = 0; e < 3; e++) {
						int gateIdx = _gateDeltas[g][e];
						enter(_gates[gateIdx][0], _gates[gateIdx][1], _dirDeltas[d][e]);
					}
					_answer = Math.min(_answer, _sum);
				}
			}

			output.append("#").append(tc).append(" ").append(_answer).append("\n");

		} // tc

		System.out.println(output);

	}

}
