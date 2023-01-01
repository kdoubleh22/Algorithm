package swea.d4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author kdoubleh22
 * @see https://www.acmicpc.net/problem/2206
 * @performance
 * @category #
 * @memo 문제 꼼꼼히 읽기. dfs+dp 유형. 디버깅할때 머리로만 하지말고 오랫동안 안 풀리면 프린트로 찍어보기. 전역변수 선언해놓고
 *       메인함수 안에서 또 type 선언하지않기. 내가 갈 수 있는 방향이 여기서는 없거나 한 개이다. Math.min 함수쓰면
 *       idf문 안 쓰고 작은거 구할수있음. 기존 갔던 길을 저장하고, 경유지면 해볼필요없고, 얼마나 갈수있었는지 저장.
 *       memoization? 이런걸 직관적으로 알기는 쉽지 않음. 주어진 테케를 시뮬레이션 해보는게 필요. 규칙을 발견하면 아이디어가
 *       나올 수 있고, 메모이제이션을 떠올릴수있음. 습관이 중요.
 */

public class SWEA_D4_01861 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int n, roomNumber, max, result;
	static int[][] A;
	static boolean[][] isVisited;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 위에서부터 시계방향

	static boolean isIn(int r, int c) {
//		System.out.println("r:"+r+"c:"+c+"n:"+n);
		return r >= 0 && r < n && c >= 0 && c < n;
	}

	static void dfs(int r, int c, int distance) {
//		System.out.println("r:"+r+",c:"+c+",distance:"+distance);//d

		for (int i = 0; i < 4; i++) {
			int nr = r + deltas[i][0];
			int nc = c + deltas[i][1];
//			System.out.println("isIn:" + isIn(nr, nc));
//			System.out.println("isVisited[nr][nc] == false:"+(isVisited[nr][nc] == false));
//			System.out.println("A[nr][nc] == A[r][c] + 1"+(A[nr][nc] == A[r][c] + 1));
			if (isIn(nr, nc) && isVisited[nr][nc] == false && A[nr][nc] == A[r][c] + 1) {
//				System.out.println("in");// d
				isVisited[nr][nc] = true;
				dfs(nr, nc, distance + 1);
				isVisited[nr][nc] = false;
			} else {
				if (distance > result) {
					result = distance;
				}
			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
//		input = new BufferedReader(new FileReader("./swea01861input.txt"));// d
		int t = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(input.readLine());
			A = new int[n][n];
			for (int i = 0; i < n; i++) {
				tokens = new StringTokenizer(input.readLine());
				for (int j = 0; j < n; j++) {
					A[i][j] = Integer.parseInt(tokens.nextToken());
				}
			} // 입력 끝.

			roomNumber = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			result = -1;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					isVisited = new boolean[n][n];
					isVisited[i][j] = true;
					result = -1;
					dfs(i, j, 1);
					if (result == max) {
						if (A[i][j] < roomNumber) {
							roomNumber = A[i][j];
						}
					} else if (result > max) {
						roomNumber = A[i][j];
						max = result;
					}
				}
			}
			output.append("#").append(tc).append(" ").append(roomNumber).append(" ").append(max).append("\n");

		}
		System.out.print(output);

	}

}
