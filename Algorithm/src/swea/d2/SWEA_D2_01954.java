package swea.d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author me
 * @see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PobmqAPoDFAUq
 * @performance 16,088 kb 113 ms
 * @category 
 * @memo 변수 헷갈리지 않기.
 * 
 */

public class SWEA_D2_01954 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // right down left up

	static boolean isIn(int r, int c, int n) {
		return r >= 0 && r < n && c >= 0 && c < n;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		int t = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= t; tc++) {
			int n = Integer.parseInt(input.readLine());
			int[][] snails = new int[n][n];
			int num = 1;
			int r = 0;
			int c = 0;
			int direction = 0;
			while (num <= n*n) {
//				System.out.println("r:"+r+",c:"+c);//d
				snails[r][c] = num;
				num++;
				int nr = r + deltas[direction][0];
				int nc = c + deltas[direction][1];
				if (!isIn(nr, nc, n) || snails[nr][nc]!=0) {
//					System.out.println("in");//d
					direction=(direction+1)%4;
					nr = r + deltas[direction][0];
					nc = c + deltas[direction][1];
//					System.out.println("nr: "+nr+",nc: "+nc);//d
				}
				r = nr;
				c = nc;
			}
			output.append("#").append(tc).append("\n");
			for (int i = 0; i < n; i++) {
				for(int j=0;j<n;j++) {
					output.append(snails[i][j]).append(" ");
				}
				output.append("\n");
			}
		}
		System.out.print(output);
	}

}
