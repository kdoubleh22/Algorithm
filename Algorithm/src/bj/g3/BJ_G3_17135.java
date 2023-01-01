package bj.g3;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 
 * @author kdoubleh22
 * @see https://www.acmicpc.net/problem/1260
 * @performance 35740 KB 632 ms
 * @category #그래프 이론 #그래프 탐색 #너비 우선 탐색 #깊이 우선 탐색
 * @memo List 내림차순 정렬하려면, 'Collections.sort(list, Collections.reverseOrder());'.
 *       도저히 로직이 어디서 틀린지 모르겠을 땐, 변수들 확인해보기. 'for (int i = start; i < _m; i++)
 *       {'에서 _m 대신 _n을 넣어 헤맸음. 평소였으면 에러가 났겠지만, 이 문제에선 조합 결과를 궁수의 거리 계산하는데에만 쓰였을
 *       뿐, 배열에 접근하지 않았기 때문에 찾기 어려웠다. 적들 대신 궁수를 이동시킬수도 있다. 깊은 복사할때 이중for문 사용해도
 *       속도에 차이 없음.
 * 
 */

public class BJ_G3_17135 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n, _m, _d, _answer, _temp;
	static int[][] _board;
	static List<Point> _enemies;
	static int[] _result;

	static int findDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	static void gameStart() {
//		System.out.println(Arrays.toString(_result));

		// _enemies 복사.
		List<Point> tempEnemies = new LinkedList<>();
		for (Point enemy : _enemies) {
			tempEnemies.add(new Point(enemy.x, enemy.y));
		}

		while (!tempEnemies.isEmpty()) {
			// 궁수 공격
			Set<Integer> willDie = new HashSet<>();
			for (int c : _result) {
				// 가장 가까운 적 찾기.
				int min = Integer.MAX_VALUE;
				int minIdx = -1; // 제일 가까운 적의 idx.
				int minColIdx = Integer.MAX_VALUE;
				for (int j = 0, size = tempEnemies.size(); j < size; j++) {
					int distance = findDistance(_n, c, tempEnemies.get(j).x, tempEnemies.get(j).y);
					if (distance <= _d) {
						if (distance < min) { // 제일 가까운 적.
							min = distance;
							minIdx = j;
							minColIdx = tempEnemies.get(j).y;
						} else if (distance == min) { // 거리가 같으면, col 비교, 제일 왼쪽 적 찾기.
							if (tempEnemies.get(j).y < minColIdx) {
								minIdx = j;
								minColIdx = tempEnemies.get(j).y;
							}
						}
					}
				}
				// 가장 가까운 적 담기. 겹칠수도 있으니까 set에
				if (minIdx != -1) { // -1인 경우는 공격할 수 있는 적이 없다.
					willDie.add(minIdx);
				}
			}
			// 제거 수만큼 더하기.
			_temp += willDie.size();

			// 적 제거
			List<Integer> tempList = new ArrayList<>();
			for (int idx : willDie) {
				tempList.add(idx);
			}
			Collections.sort(tempList, Collections.reverseOrder());
			for (int idx : tempList) {
				tempEnemies.remove(idx);
			}

//			System.out.println("제거후\n" + Arrays.toString(tempEnemies.toArray()));

			// 적 한 칸 아래로 이동. 맵에서 이탈하면 제외.
			for (int i = tempEnemies.size() - 1; i >= 0; i--) {
				if (tempEnemies.get(i).x == _n - 1) {
					tempEnemies.remove(i);
				} else {
					tempEnemies.get(i).x += 1;
				}
			}

//			System.out.println("이동후\n" + Arrays.toString(tempEnemies.toArray()));

		}
//		System.out.println(_temp);
	}

	static void comb(int cnt, int start) {
		if (cnt == 3) {
			_temp = 0;
			gameStart();
			_answer = Math.max(_answer, _temp);
			return;
		}
		for (int i = start; i < _m; i++) {
			_result[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_m = Integer.parseInt(tokens.nextToken());
		_d = Integer.parseInt(tokens.nextToken());

		_board = new int[_n][_m];
		_enemies = new LinkedList<Point>();
		for (int i = 0; i < _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < _m; j++) {
				_board[i][j] = Integer.parseInt(tokens.nextToken());
				if (_board[i][j] == 1) {
					_enemies.add(new Point(i, j));
				}
			}

		}

		_result = new int[3];
		_answer = Integer.MIN_VALUE;
		comb(0, 0);

		output.append(_answer);
		System.out.println(output);
	}

}
