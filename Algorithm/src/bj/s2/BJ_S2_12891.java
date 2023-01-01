package bj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author me
 * @see https://www.acmicpc.net/problem/12891
 * @performance 20432 KB	196 ms
 * @category #슬라이딩 윈도우
 * @memo memoization? 전에 값들을 저장해서 window 한 칸씩 이동하면서 update.
 * 
 */

public class BJ_S2_12891 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int s, p;
	static String dna;
	static int[] minNum;

	static int charToIdx(char c) {
		if (c == 'A') {
			return 0;
		} else if (c == 'C') {
			return 1;
		} else if (c == 'G') {
			return 2;
		} else if (c == 'T') {
			return 3;
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		s = Integer.parseInt(tokens.nextToken());
		p = Integer.parseInt(tokens.nextToken());
		dna = input.readLine();
		minNum = new int[4];
		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < 4; i++) {
			minNum[i] = Integer.parseInt(tokens.nextToken());
		}

		int[] subNum = new int[4];
		// 초기값 세팅
		for (int i = 0; i < p; i++) {
			subNum[charToIdx(dna.charAt(i))]++;
		}
		int answer = 0;
		if (subNum[0] >= minNum[0] && subNum[1] >= minNum[1] && subNum[2] >= minNum[2] && subNum[3] >= minNum[3]) {
			answer++;
		}
//		System.out.println(Arrays.toString(subNum));//d

		for (int i = 1; i <= s - p; i++) {
			subNum[charToIdx(dna.charAt(i - 1))]--;
			subNum[charToIdx(dna.charAt(i + p - 1))]++;
			if (subNum[0] >= minNum[0] && subNum[1] >= minNum[1] && subNum[2] >= minNum[2] && subNum[3] >= minNum[3]) {
				answer++;
			}
//			System.out.println(Arrays.toString(subNum));//d
		}
		output.append(answer);
		System.out.print(output);

	}

}
