package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 있는지 없는지 학인하는건 set이 편함.
 * 근데 순서대로 몇번 째 열인지 계산하려면 각 인덱스를 가지고 있어야 됨. 문자도 가지고 있어야 set에 넣고 뺼 수 있다.
 */

public class BJ_G4_16472 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static char[] _s;
	static Set<Character> _alphabets;
	static Map<Character, Integer> _locations;

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine()); // 2 <= n <= 26

		_s = input.readLine().toCharArray();
		int l = _s.length;

		_alphabets = new HashSet<>();
		_locations = new HashMap<>();

		int length = 0;
		int answer = Integer.MIN_VALUE;
		for (int i = 0; i < l; i++) {
			char c = _s[i];
			if (_alphabets.contains(c)) { // c가 set에 이미 있다면,
				// c의 마지막 위치 업데이트.
				_locations.put(c, i);
				length += 1;
				answer = Math.max(answer, length);
			} else { // c가 set에 없다면,
				// 꽉 찼다면,
				if (_alphabets.size() == _n) {
					// 하나를 빼고 넣음. 빼는 기준은 가장 쓰인지 오래 된 알파벳. Least Recently Used
					char tempC = 'A'; // 가장 안 쓰인 알파벳
					int tempI = Integer.MAX_VALUE;
					for (char ch : _locations.keySet()) {
						if (_locations.get(ch) < tempI) {
							tempI = _locations.get(ch);
							tempC = ch;
						}
					}
					// 가장 안 쓰인 알파벳(tempC)를 빼고,
					_alphabets.remove(tempC);
					_locations.remove(tempC);
					// 현재 알파벳을 넣어줌.
					_alphabets.add(c);
					_locations.put(c, i);
					// 길이 업데이트.
					length = i - tempI;
				}
				// 꽉 차지 않았다면, 그냥 넣음.
				else {
					_alphabets.add(c);
					_locations.put(c, i);
					length += 1;
					answer = Math.max(answer, length);
				}
			}
		}

		System.out.println(answer);

	}

}