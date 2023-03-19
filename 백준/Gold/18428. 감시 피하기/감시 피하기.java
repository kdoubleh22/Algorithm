import java.io.*;
import java.util.*;

/*
 * -문제
 * 조합
 * -풀이
 * 빈 칸을 ArrayList에 담고, 조합을 돌려서 가능하면 멈춘다. 다 돌릴 때까지 가능하지 않으면 NO.
 * 선생님을 돌면서 상하좌우로 학생이 있는지 확인하는 함수 만듦.
 */

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상하좌우

	static int _n; // 3 <= N <= 6
	static char[][] _map;
	static List<int[]> _vacancies;
	static List<int[]> _teachers;
	static String _answer;
	static boolean _isFound;

	// 장애물 세우는 함수
	static void setObstacles(int[] indices) {
		for (int index : indices) {
			int r = _vacancies.get(index)[0];
			int c = _vacancies.get(index)[1];
			_map[r][c] = 'O';
		}
	}

	// 장애물 없애는 함수
	static void removeObstacles(int[] indices) {
		for (int index : indices) {
			int r = _vacancies.get(index)[0];
			int c = _vacancies.get(index)[1];
			_map[r][c] = 'X';
		}
	}

	static boolean isIn(int r, int c) {
		return 0 <= r && r < _n && 0 <= c && c < _n;
	}

	// 선생님의 좌표를 받아서 상하좌우로 학생이 있는지 확인. 학생이 찾으면 true return.
	static boolean findStudent(int r, int c) {
		boolean result = false;

		// 4방향을 돌면서,
		for (int d = 0; d < 4; d++) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];
			while (isIn(nr, nc)) {
				// 학생일 때,
				if (_map[nr][nc] == 'S') {
					return true;
				}
				// 선생님일 때,
				else if (_map[nr][nc] == 'T') {
					break;
				}
				// 장애물일 때,
				else if (_map[nr][nc] == 'O') {
					break;
				}
				// 빈 칸일 때,
				else {
					// 아무것도 하지 않음.
				}
				// 좌표 업데이트.
				nr = nr + deltas[d][0];
				nc = nc + deltas[d][1];
			}
		}

		return result;
	}

	// 모든 학생들이 감시로부터 피할 수 있는지.
	static boolean canAvoid() {
		boolean result = true;

		// 선생들의 좌표를 돌면서, 상하좌우로 학생들이 없는지 확인.
		for (int[] teacher : _teachers) {
			// 선생님의 상하좌우를 보면서, 학생을 발견하면 함수는 false를 return하고 종료.
			if (findStudent(teacher[0], teacher[1])) {
				return false;
			}
		}

		return result;
	}

	// cnt: 몇개 골랏는지,
	// start: 조합 시작접.
	// indices: 골라진 장애물 index.
	static void comb(int cnt, int start, int[] indices) {
		// 찾았다면 더 돌 필요 없음.
		if (_isFound) {
			return;
		}
		// 장애물 3개 골랐을 때.
		if (cnt == 3) {
			// 장애물 3개 세우기.
			setObstacles(indices);

			// 학생들이 다 가려지는지 확인. 만약 다 가려진다면 더 돌 필요 없음.
			if (canAvoid()) {
				_answer = "YES";
				_isFound = true;
			}
			// map 원상복구.
			removeObstacles(indices);
			return;
		}

		for (int i = start; i < _vacancies.size(); i++) {
			indices[cnt] = i;
			comb(cnt + 1, i + 1, indices);
		}
	}

	public static void main(String[] args) throws Exception {
		_n = Integer.parseInt(input.readLine());

		_map = new char[_n][_n];
		_vacancies = new ArrayList();
		_teachers = new ArrayList();
		for (int r = 0; r < _n; r++) {
			tokens = new StringTokenizer(input.readLine(), " ");
			for (int c = 0; c < _n; c++) {
				_map[r][c] = tokens.nextToken().charAt(0);
				if (_map[r][c] == 'X') {
					_vacancies.add(new int[] { r, c });
				} else if (_map[r][c] == 'T') {
					_teachers.add(new int[] { r, c });
				}
			}
		}

		_answer = "NO";
		_isFound = false;
		comb(0, 0, new int[3]);

		System.out.println(_answer);
	}

}