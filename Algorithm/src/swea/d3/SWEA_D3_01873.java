package swea.d3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author me
 * @see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LyE7KD2ADFAXc
 * @performance 20,736 kb 143 ms
 * @category 
 * @memo 입력받을때 공백있는지 확인하기. 전역변수 쓰는거 고려해보기.(매번 함수 인자로 전달하기 귀찮음).
 * 		 'char[][] map = new char[h][];' 뒤에 쓸 필요 없음. String.indexof 함수?
 */

public class SWEA_D3_01873 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int r;
	static int c;
	static int dir = 0; // 0:up 1:down 2:left r:right
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 0:up 1:down 2:left r:right
	static char[] charDir = { '^', 'v', '<', '>' };
	
	static void shoot(char[][] map, int h, int w) {
		int i = 1;
		while (true) {

			// 포탄 날아감
			int nr = r + i * deltas[dir][0];
			int nc = c + i * deltas[dir][1];
			if (isIn(nr, nc, h, w)) {
				if (map[nr][nc] == '*') {
					map[nr][nc] = '.';
					return;
				} else if (map[nr][nc] == '#') {
					return;
				}
			} else { // 맵 밖으로 날아가면 종료
				return;
			}
			i++;
		}
	}

	static boolean isIn(int r, int c, int h, int w) {
		return r >= 0 && r < h && c >= 0 && c < w;
	}

	static void move(char[][] map, int direction, int h, int w) {
		// 방향 바꾸기
		if (direction == 0) {
			map[r][c] = '^';
			dir = 0;
		} else if (direction == 1) {
			map[r][c] = 'v';
			dir = 1;
		} else if (direction == 2) {
			map[r][c] = '<';
			dir = 2;
		} else if (direction == 3) {
			map[r][c] = '>';
			dir = 3;
		}
		// 이동
		int nr = r + deltas[dir][0];
		int nc = c + deltas[dir][1];
		if (isIn(nr, nc, h, w) && map[nr][nc] == '.') {
			map[nr][nc] = charDir[dir];
			map[r][c] = '.';
			r = nr;
			c = nc;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		input = new BufferedReader(new FileReader("./swea_01873_input.txt"));//d
		int t = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= t; tc++) {
			tokens = new StringTokenizer(input.readLine());
			int h = Integer.parseInt(tokens.nextToken());
			int w = Integer.parseInt(tokens.nextToken());
//			System.out.println("h:"+h+",w:"+w);//d
			char[][] map = new char[h][];
			for (int i = 0; i < h; i++) {
				map[i] = input.readLine().toCharArray();
			}
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == '^') {
						r = i;
						c = j;
						dir = 0;
					} else if (map[i][j] == 'v') {
						r = i;
						c = j;
						dir = 1;
					} else if (map[i][j] == '<') {
						r = i;
						c = j;
						dir = 2;
					} else if (map[i][j] == '>') {
						r = i;
						c = j;
						dir = 3;
					}
				}
			}
//			for(int i=0;i<h;i++) {
//				for(int j=0;j<w;j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}
			int n = Integer.parseInt(input.readLine());
			String str = input.readLine();
			for (int i = 0; i < n; i++) {
				if (str.charAt(i) == 'U') {
					move(map, 0, h, w);
				} else if (str.charAt(i) == 'D') {
					move(map, 1, h, w);
				} else if (str.charAt(i) == 'L') {
					move(map, 2, h, w);
				} else if (str.charAt(i) == 'R') {
					move(map, 3, h, w);
				} else if (str.charAt(i) == 'S') {
					shoot(map, h, w);
				}
			}
			output.append(String.format("#%d ", tc));
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					output.append(map[i][j]);
				}
				output.append("\n");
			}

		} // tc
		System.out.print(output);
	}

}
