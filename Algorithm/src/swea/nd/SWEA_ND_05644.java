package swea.nd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 
 * @author kdoubleh22
 * @see https://www.acmicpc.net/problem/2206
 * @performance 23,488 kb 123 ms
 * @category #구현
 * @memo 문제풀이에 클래스를 구현해서 정렬하면 편한 것 같다. 큰 순서대로 뺄 때는 Priority Queue. 클래스 만들 때
 *       toString도 override 해주면, 디버깅할때 편함.
 * 
 */

public class SWEA_ND_05644 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] deltas = { { 0, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } }; // 제자리 상 우 하 좌

	static int _t, _m, _a;
	static int[] _moveA, _moveB;
	static Battery[] batteries;

	static class Battery implements Comparable<Battery> {
		int x, y, c, p;

		public Battery(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}

		@Override
		public int compareTo(Battery o) {
			return Integer.compare(this.p, o.p) * -1; // 내림차순 정렬.
		}
	}

	static int findDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
//		input = new BufferedReader(new FileReader("./io/swea05644input.txt"));// d

		_t = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= _t; tc++) {
			tokens = new StringTokenizer(input.readLine());
			_m = Integer.parseInt(tokens.nextToken());
			_a = Integer.parseInt(tokens.nextToken());

			_moveA = new int[_m + 1];
			tokens = new StringTokenizer(input.readLine());
			for (int i = 1; i < _m + 1; i++) {
				_moveA[i] = Integer.parseInt(tokens.nextToken());
			}

			_moveB = new int[_m + 1];
			tokens = new StringTokenizer(input.readLine());
			for (int i = 1; i < _m + 1; i++) {
				_moveB[i] = Integer.parseInt(tokens.nextToken());
			}

			batteries = new Battery[_a];
			for (int i = 0; i < _a; i++) {
				tokens = new StringTokenizer(input.readLine());
				batteries[i] = new Battery(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()),
						Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
			} // 입력 끝.

			Arrays.sort(batteries);

			int aX = 1;
			int aY = 1;
			int bX = 10;
			int bY = 10;
			int answer = 0;
			for (int i = 0; i < _m + 1; i++) {
				// 이동
				for (int j = 0; j < 5; j++) {
					if (_moveA[i] == j) {
						aX += deltas[j][0];
						aY += deltas[j][1];
					}
					if (_moveB[i] == j) {
						bX += deltas[j][0];
						bY += deltas[j][1];
					}
				}

				// 배터리를 돌면서 충전할 수 있는지 확인하고
				int availA = 0;
				int availB = 0;
				// 현재 위치에서 충전가능한 배터리 개수
				for (int j = 0; j < _a; j++) {
					if (findDistance(batteries[j].x, batteries[j].y, aX, aY) <= batteries[j].c) {
						availA++;
					}
					if (findDistance(batteries[j].x, batteries[j].y, bX, bY) <= batteries[j].c) {
						availB++;
					}
				}

				int batteryAIdx = -1;
				int batteryBIdx = -1;
				// 일단 제일 큰걸로 더해주고,
				if (availA >= 1) {
					for (int j = 0; j < _a; j++) {
						if (findDistance(batteries[j].x, batteries[j].y, aX, aY) <= batteries[j].c) {
							answer += batteries[j].p;
							batteryAIdx = j;
							break;
						}
					}
				}
				if (availB >= 1) {
					for (int j = 0; j < _a; j++) {
						if (findDistance(batteries[j].x, batteries[j].y, bX, bY) <= batteries[j].c) {
							answer += batteries[j].p;
							batteryBIdx = j;
							break;
						}
					}
				}
				// 겹치면, 다른걸로 대체할 수 있는지 확인.
				if ((batteryAIdx != -1) && (batteryAIdx == batteryBIdx)) {
					if (availA == 1 && availB == 1) { // 둘 다 1개일 때
						answer -= batteries[batteryAIdx].p;
					} else if (availA >= 2 && availB == 1) { // A는 2개이상 가능하고, B는 1개일 때.
						answer -= batteries[batteryAIdx].p; // 아까 더했던 걸 빼주고,
						for (int j = batteryAIdx + 1; j < batteries.length; j++) {
							if (findDistance(batteries[j].x, batteries[j].y, aX, aY) <= batteries[j].c) {
								answer += batteries[j].p; // 다음걸로 더해줌.
								break;
							}
						}
					} else if (availA == 1 && availB >= 2) { // A는 1개이고, B는 2개이상 가능할 때.
						answer -= batteries[batteryBIdx].p; // 아까 더했던 걸 빼주고,
						for (int j = batteryBIdx + 1; j < batteries.length; j++) {
							if (findDistance(batteries[j].x, batteries[j].y, bX, bY) <= batteries[j].c) {
								answer += batteries[j].p; // 다음걸로 더해줌.
								break;
							}
						}
					} else { // 둘 다 2개 이상일 때, 각자 다음 큰 걸 찾아서 비교 후 더해줌.
						answer -= batteries[batteryAIdx].p;
						for (int j = batteryAIdx + 1; j < batteries.length; j++) {
							if (findDistance(batteries[j].x, batteries[j].y, aX, aY) <= batteries[j].c) {
								answer += batteries[j].p; // 다음걸로 더해줌.
								break;
							}
							if (findDistance(batteries[j].x, batteries[j].y, bX, bY) <= batteries[j].c) {
								answer += batteries[j].p; // 다음걸로 더해줌.
								break;
							}
						}
					}

				}
			} // for i
			output.append("#").append(tc).append(" ").append(answer).append("\n");

		} // for tc
		System.out.print(output);
	}

}
