package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author kdoubleh22
 * @see https://www.acmicpc.net/problem/17406
 * @performance 1.Arrays.stream().sum() 사용시: 34116 KB 428 ms.
 * @performance 2.for문으로 sum 구할시: 28300 KB 236 ms.
 * @performance 3. 2에서 System.arraycopy대신 Arrays.copyof 사용시: 36328 KB 236 ms
 * @category #
 * @memo 편하게 구하기 위해서 배열의 합을 Arrays.stream(array_name).sum()으로 구할수 있지만, 속도가 느려서 안
 *       쓰는게 나을 것 같다. 'https://brorica.tistory.com/entry/java-stream' 참고. 자바
 *       'int[][] temp = A' 참조형 변수는 이렇게 복사해봤자 주소가 들어가서 의미가 없음. 이런 실수 하지 않기.
 *       2차원배열 깊은복사는 이중for문이나,
 *       System.arraycopy(src,srcPos,dest,destPos,length)사용하기. Arrays.copyof 는
 *       내부를 보면 System.arraycopy를 호출하여 복사하기 때문에 성능이 비슷하다. 이 문제에 국한된 것인지는 모르겠찌만,
 *       속도는 거의 비슷하나 메모리사용에 있어서 System.arraycopy가 메모리를 덜 사용했다. 웬만하면
 *       System.arraycopy 쓰는걸로 습관 들이자.
 */

public class BJ_G4_17406 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int N, M, K, min;
	static int[][] A, operations, permResult;
	static boolean[] isVisited;

	static void findMin() {
		for (int i = 1; i < N + 1; i++) {
			int sum = 0;
			for (int j = 1; j < M + 1; j++) {
				sum += A[i][j];
			}
			if (sum < min) {
				min = sum;
			}
		}
	}

	static void rotate(int r, int c, int s) {
		for (int i = 1; i <= s; i++) {
			int temp = A[r - i][c - i];
			for (int row = r - i; row < r + i; row++) {
				A[row][c - i] = A[row + 1][c - i];
			}
			for (int col = c - i; col < c + i; col++) {
				A[r + i][col] = A[r + i][col + 1];
			}
			for (int row = r + i; row > r - i; row--) {
				A[row][c + i] = A[row - 1][c + i];
			}
			for (int col = c + i; col > c - i + 1; col--) {
				A[r - i][col] = A[r - i][col - 1];
			}
			A[r - i][c - i + 1] = temp;
		}
	}

	static void permutation(int cnt) {
		if (cnt == K) {
			int[][] temp = new int[N + 1][M + 1];
			for (int i = 0; i < A.length; i++) {
//				temp[i] = Arrays.copyOf(A[i], A[0].length);
				System.arraycopy(A[i], 0, temp[i], 0, A[0].length);
			}
			for (int i = 0; i < K; i++) {
				rotate(permResult[i][0], permResult[i][1], permResult[i][2]);
			}
			findMin();
			A = temp;
			return;
		}

		for (int i = 0; i < K; i++) {
			if (!isVisited[i]) {
				isVisited[i] = true;
				permResult[cnt] = operations[i];
				permutation(cnt + 1);
				isVisited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());

		A = new int[N + 1][M + 1];
		for (int i = 1; i < A.length; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 1; j < A[0].length; j++) {
				A[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}

		operations = new int[K][3];
		for (int i = 0; i < K; i++) {
			tokens = new StringTokenizer(input.readLine());
			operations[i][0] = Integer.parseInt(tokens.nextToken());
			operations[i][1] = Integer.parseInt(tokens.nextToken());
			operations[i][2] = Integer.parseInt(tokens.nextToken());
		} // 입력 끝.

		permResult = new int[K][3];
		isVisited = new boolean[K];
		min = Integer.MAX_VALUE;
		permutation(0);

		output.append(min);
		System.out.print(min);
	}

}
