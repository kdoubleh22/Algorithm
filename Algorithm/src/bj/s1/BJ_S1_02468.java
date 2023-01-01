package bj.s1;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author me
 * @see https://www.acmicpc.net/problem/2468
 * @performance 39384 KB	288 ms
 * @category #그래프 이론 #브루트포스 알고리즘 #그래프 탐색 #너비 우선 탐색 #깊이 우선 탐색
 * @memo 웬만하면 BFS로 풀자. 성능이 더 좋으니까. q에 넣을때 visited true 처리. 조건 빠트리지말기(&&
 *       submerge[nr][nc] == 1), 변수혼동하지말자(네이밍 잘할것)-map,submerge. queue에 중복으로
 *       들어가는거 메모리초과 조심하기. 문제 꼼꼼히 읽기. '아무 지역도 물에 잠기지 않을 수도 있다.' 최소 1이다.
 * 
 */

public class BJ_S1_02468 {

		static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		static StringBuilder output = new StringBuilder();
		static StringTokenizer tokens;

		static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상 하 좌 우

		static int n;
		static int[][] map, submerge;

		static boolean isIn(int r, int c) {
			return r >= 0 && r < n && c >= 0 && c < n;
		}

		static int bfs() {
			int safeNum = 0;
			Queue<Point> q = new ArrayDeque<>();
			boolean[][] visited = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (submerge[i][j] == 1) { // 잠겨있지 않음.
						if (visited[i][j] == false) { // 방문한 적 없음.
							q.offer(new Point(i, j)); // 자기를 넣고,
							visited[i][j] = true; // 방문처리.
							while (!q.isEmpty()) { // q가 빌 때까지,
								Point polled = q.poll(); // 하나를 빼서
								int r = polled.x;
								int c = polled.y;
								for (int l = 0; l < 4; l++) {
									int nr = r + deltas[l][0];
									int nc = c + deltas[l][1];
									if (isIn(nr, nc) && visited[nr][nc] == false && submerge[nr][nc] == 1) {
										q.offer(new Point(nr, nc));
										visited[nr][nc] = true;
									}
								}
							}
							// q가 비면 탐색이 끝난거고 safeNum 1증가.
							safeNum += 1;
						} else { // 방문한 적 있을 때.
							continue;
						}
					} else { // 0: 잠겨있을때,
						continue;
					}
				}
			}

			return safeNum;
		}

		static int find(int h) {
			// 물에 잠긴곳은 0 안전한곳은 1.
			submerge = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] <= h) {
						submerge[i][j] = 0;
					} else {
						submerge[i][j] = 1;
					}
				}
			}

			return bfs();
		}

		public static void main(String[] args) throws NumberFormatException, IOException {
			n = Integer.parseInt(input.readLine());
			map = new int[n][n];
			for (int i = 0; i < n; i++) {
				tokens = new StringTokenizer(input.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
				}
			} // 입력 끝.

			int answer = 0;
			for (int h = 0; h <= 100; h++) {
				int result = find(h);
				if (result == 0) { // 다 잠겼다는 뜻. 그럼 break.
					break;
				}
				if (result > answer) {
					answer = result;
				}

			}
			output.append(answer);
			System.out.print(answer);

		}

	}
