package bj.g5;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author kdoubleh22
 * @see https://www.acmicpc.net/problem/15686
 * @performance 1. Math.min 사용시: 13552 KB 184 ms 2. Math.min 대신 if문 사용시: 13540
 *              KB 168 ms 3. 삼항연산자: 12892 KB 172 ms
 * @category #구현 #브루트포스 #백트래킹
 * @memo 재귀함수 쓸 때, 종료조건에서 return 까먹지 말기. 맨하탄거리: |r1-r2|+|y1-y2| 이런 꼴. 이 문제만 보면
 *       조건문 쓰는게 가장 빨랐다.
 * 
 */

public class BJ_G5_15686 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int N, M, min;
	static int[][] city;
	static List<Point> stores, homes;
	static int[] choosed;

	static int findCityChickenDistance() {
		int sum = 0;
		for (int i = 0; i < homes.size(); i++) {
			int minHomeCD = Integer.MAX_VALUE;
			for (int num : choosed) {
				int sx = stores.get(num).x;
				int sy = stores.get(num).y;
				int hx = homes.get(i).x;
				int hy = homes.get(i).y;
				int cD = Math.abs(sx - hx) + Math.abs(sy - hy);
//				minHomeCD = Math.min(minHomeCD, Math.abs(sx - hx) + Math.abs(sy - hy)); // Case 1
				if (cD < minHomeCD) { // Case 2
					cD = minHomeCD;
				}
//				minHomeCD = (cD < minHomeCD) ? cD : minHomeCD; // Case 3

			}
			sum += minHomeCD;
			if (sum > min) {
				return Integer.MAX_VALUE;
			}
		}
		return sum;
	}

	static void comb(int cnt, int start) {
		if (cnt == M) {
			int result = findCityChickenDistance();
			if (result < min) {
				min = result;
			}
			return;
		}

		for (int i = start; i < stores.size(); i++) {
			choosed[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		stores = new ArrayList<>();
		homes = new ArrayList<>();
		city = new int[N][N];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < N; j++) {
				city[i][j] = Integer.parseInt(tokens.nextToken());
				if (city[i][j] == 1) {
					homes.add(new Point(i, j));
				} else if (city[i][j] == 2) {
					stores.add(new Point(i, j));
				}
			}
		} // 입력 끝.

		choosed = new int[M];
		min = Integer.MAX_VALUE;
		comb(0, 0);

		output.append(min);
		System.out.print(min);

	}

}
