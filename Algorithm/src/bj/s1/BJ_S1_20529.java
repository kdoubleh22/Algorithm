package bj.s1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * -풀이
 * Set + 조합
 * 1종류, 2종류일 때 에외.
 */

public class BJ_S1_20529 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _t;
	static int _n;
	static Set<String> _mbtis;
	static String[] _mbtisArr;
	static int _answer;
	static Map<String, Integer> _mbtiCnt;

	static int findDist(String m1, String m2) {
		int result = 0;

		for (int i = 0; i < 4; i++) {
			if (m1.charAt(i) != m2.charAt(i)) {
				result += 1;
			}
		}

		return result;
	}

	static void comb(int cnt, int start, String[] result, String[] arr) {
		// 종료조건
		if (cnt == 3) {
			int sum = findDist(result[0], result[1]) + findDist(result[1], result[2]) + findDist(result[2], result[0]);
			_answer = Integer.min(_answer, sum);
			return;
		}

		// 조합
		for (int i = start; i < arr.length; i++) {
			result[cnt] = arr[i];
			comb(cnt + 1, i + 1, result, arr);
		}
	}

	public static void main(String[] args) throws Exception {
		_t = Integer.parseInt(input.readLine());

		while (_t-- > 0) {
			_n = Integer.parseInt(input.readLine());

			tokens = new StringTokenizer(input.readLine());

			_mbtis = new HashSet<>();
			_mbtiCnt = new HashMap<>();

			for (int i = 0; i < _n; i++) {
				String mbti = tokens.nextToken();
				_mbtis.add(mbti);
				// 있는 mbti면 개수를 가져와서 1을 더한 후 넣어준다.
				if (_mbtiCnt.containsKey(mbti)) {
					_mbtiCnt.put(mbti, _mbtiCnt.get(mbti) + 1);
				}
				// 없는 mbti면 cnt를 1로 put해준다.
				else {
					_mbtiCnt.put(mbti, 1);
				}
			}

			_mbtisArr = new String[_mbtis.size()];
			int idx = 0;
			for (String s : _mbtis) {
				_mbtisArr[idx++] = s;
			}

			_answer = Integer.MAX_VALUE;

			boolean hasThree = false;
			boolean hasTwo = false;
			List<String> twos = new LinkedList<String>();
			int twoCnt = 0;
			for (String key : _mbtiCnt.keySet()) {
				if (_mbtiCnt.get(key) >= 3) {
					hasThree = true;
					continue;
				}
				if (_mbtiCnt.get(key) >= 2) {
					hasTwo = true;
					twoCnt += 1;
					twos.add(key);
				}
			}

			if (hasThree) {
				output.append(0).append("\n");
			} else if (hasTwo) {
				String[] newMbtiArr = new String[_mbtisArr.length + twoCnt];
				for (int i = 0; i < _mbtisArr.length; i++) {
					newMbtiArr[i] = _mbtisArr[i];
				}

				int idx2 = _mbtisArr.length;
				for (String s : twos) {
					newMbtiArr[idx2++] = s;
				}

				comb(0, 0, new String[3], newMbtiArr);

				output.append(_answer).append("\n");
			} else {
				comb(0, 0, new String[3], _mbtisArr);
				output.append(_answer).append("\n");
			}

//			if (_mbtisArr.length == 1) {
//				output.append(0).append("\n");
//			} else if (_mbtisArr.length == 2) {
//				boolean hasThree = false;
//				
//				if (hasThree) {
//					output.append(0).append("\n");
//				} else {
//					output.append(2 * findDist(_mbtisArr[0], _mbtisArr[1]));
//				}
//			} else { // _mbtis.size() >= 3
//				
//				comb(0, 0, new String[3]);
//				output.append(_answer).append("\n");
//			}

		}

		System.out.println(output);
	}

}
