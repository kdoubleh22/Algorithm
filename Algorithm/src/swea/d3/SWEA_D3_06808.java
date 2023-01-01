package swea.d3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author doubleh
 * @see https://www.acmicpc.net/problem/1254
 * @performance 20,212 kb 2,709 ms 가지치기: 20,508 kb 1,098 ms
 * @category #
 * @memo 순열. 방문처리, 데이터 넣고, 갯수증가해서 재귀돌리고, 방문처리 해제. 재귀 할 때는 다 들어가볼 필요 없이, 가지치기를 할
 *       수 있으면 좋다. 가지치기 - 재귀 과정에서 안 해봐도 될 것은 미리 싹둑=> 상황에 따라 엄청난 연산 감소. 여기서는 1명이
 *       50%를 이미 가졌을 때. 가지치기 위에서 하면 편한듯. 가지치기는 바로 알기는 쉽지 않음. 생각이 많아지니까 일단 완탐을
 *       하고나서 필요한 부분 가지치기 할 수 있을지 생각해보기.
 * 
 */

public class SWEA_D3_06808 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int win, lose;
	static int[] nums, gy, iy, permArray;
	static boolean[] isVisited;

	static void perm(int cnt, int gyScore, int iyScore) {
		if (gyScore > 85 || iyScore > 85) {
			int remain = 1;
			for (int i = 9 - cnt; i >= 1; i--) {
				remain *= i;
			}
			if (gyScore > 85) {
				win += remain;
			} else if (iyScore > 85) {
				lose += remain;
			}
			return;
		}

		if (cnt == 9) {
			if (gyScore > iyScore) {
				win++;
			} else if (gyScore < iyScore) {
				lose++;
			}
			return;
		}

		for (int i = 0; i < 9; i++) {
			if (!isVisited[i]) {
				isVisited[i] = true;
//				permArray[cnt] = iy[i];
				if (gy[cnt] > iy[i]) {
					perm(cnt + 1, gyScore + gy[cnt] + iy[i], iyScore);
				} else {
					perm(cnt + 1, gyScore, iyScore + gy[cnt] + iy[i]);
				}
				isVisited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
//		input = new BufferedReader(new FileReader("./swea06808input.txt"));// d

		int t = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= t; tc++) {
			nums = new int[19];
			gy = new int[9];
			iy = new int[9];

			for (int i = 0; i < 19; i++) {
				nums[i] = i;
			}

			tokens = new StringTokenizer(input.readLine());
			for (int i = 0; i < 9; i++) {
				int num = Integer.parseInt(tokens.nextToken());
				nums[num] = -1;
				gy[i] = num;
			}

			int idx = 0;
			for (int i = 1; i < 19; i++) {
				if (nums[i] != -1) {
					iy[idx] = i;
					idx++;
				}
			}

//			System.out.println(Arrays.toString(gy));
//			System.out.println(Arrays.toString(iy));

			isVisited = new boolean[9];
			permArray = new int[9];
			win = 0;
			lose = 0;
			perm(0, 0, 0);

			output.append("#").append(tc).append(" ").append(win).append(" ").append(lose).append("\n");

		}

		System.out.print(output);
	}

}
