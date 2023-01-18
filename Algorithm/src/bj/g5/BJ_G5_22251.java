package bj.g5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * -문제
 * 1층부터 N층까지.
 * K자리의 수. (1 <= K <= 6)
 * 최소 1개 최대 P개 반전시킴. (1<=P<=42)
 * 반전 이후에 올바른 수가 되어야 하고, 숫자는 1이상 N이하.
 * 현재 엘레베이터는 실제로 X층.
 * 반전으로 만들 수 있는 수의 개수.
 * -풀이
 * 각 숫자마다 몇 번의 반전으로 다른 숫자를 만들 수 있는지 테이블을 만들어놓고. 숫자는 1이상 N이하.
 * 개수가 P이히가 되도록 부분집합.
 * 합, N으로 백트래킹. *N이하*
 */

public class BJ_G5_22251 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	// 각 숫자에 어떤 불이 켜져있는지. 위 왼쪽 순서.
	static int[][] numbers = { { 1, 1, 1, 0, 1, 1, 1 }, { 0, 0, 1, 0, 0, 1, 0 }, { 1, 0, 1, 1, 1, 0, 1 },
			{ 1, 0, 1, 1, 0, 1, 1 }, { 0, 1, 1, 1, 0, 1, 0 }, { 1, 1, 0, 1, 0, 1, 1 }, { 1, 1, 0, 1, 1, 1, 1 },
			{ 1, 0, 1, 0, 0, 1, 0 }, { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 0, 1, 1 } };

	static int _n, _k, _p, _x;
	static int[][] _flipCnt;
	static int _answer;
	static Set<Integer> _answerSet;

	static int findFlipCnt(int x, int y) {
		int result = 0;

		for (int i = 0; i < 7; i++) {
			if (numbers[x][i] != numbers[y][i]) {
				result += 1;
			}
		}

		return result;
	}

	static int resultToNum(int[] result) {
		int n = 0;
		int digit = 1;

		for (int i = result.length - 1; i > -1; i--) {
			n += result[i] * digit;
			digit *= 10;
		}

		return n;
	}

	// digit: 앞에서부터 몇 번째 숫자인지, pNum은 flip한 개수, result는 뽑은 결과값.
	static void recur(int digit, int fCnt, int[] result) {

		// 백트래킹 - 반전시킨 개수가 P보다 크거나, 숫자가 N 이상이면 return.
		if (fCnt > _p || resultToNum(result) > _n) {
			return;
		}

		// 종료 조건. _k개를 무사히 뽑았으면 _answer+=1.
		if (digit == _k) {
//			System.out.println("digit:" + digit + " fCnt:" + fCnt + " result:" + Arrays.toString(result));
			if (fCnt > 0 && resultToNum(result)!=0) {
				_answerSet.add(resultToNum(result));
			}
//			_answer += 1;
			return;
		}

		// 고르고
		for (int i = 0; i < 10; i++) {
			// 자기 자신이 아니면,
//			if (_flipCnt[result[digit]][i] != 0) {
				// 고른 경우.
				int temp = result[digit]; // 돌려놓기 위해 temp에 잠시 저장.
				result[digit] = i; // 숫자를 바궈주고,
				recur(digit + 1, fCnt + _flipCnt[temp][i], result);
				result[digit] = temp; // 돌려놓음.

				// 안 고른경우
				recur(digit + 1, fCnt, result);
//			}
		}

	}

	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_k = Integer.parseInt(tokens.nextToken());
		_p = Integer.parseInt(tokens.nextToken());
		_x = Integer.parseInt(tokens.nextToken());

		// 전처리
		_flipCnt = new int[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				_flipCnt[i][j] = findFlipCnt(i, j);
			}
		}

		// 입력 확인.
//		for (int[] row : _flipCnt) {
//			System.out.println(Arrays.toString(row));
//		}

		// 부분집합
		_answer = 0;

		int[] result = new int[_k]; // k자리수.
		int idx = _k - 1;
		while (_x > 0) {
			result[idx--] = _x % 10;
			_x /= 10;
		}

//		// 입력 확인.
//		System.out.println(Arrays.toString(result));

		_answerSet = new HashSet<>();
		recur(0, 0, result);

//		System.out.println(_answerSet);
		System.out.println(_answerSet.size());

	}

}
