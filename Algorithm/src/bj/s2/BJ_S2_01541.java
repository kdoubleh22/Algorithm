package bj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author kdoubleh22
 * @see https://www.acmicpc.net/problem/2206
 * @performance 23,488 kb 123 ms
 * @category #구현
 * @memo stringtokenizer(,,) 파싱, 인덱스
 * 
 */

public class BJ_S2_01541 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static char[] _equation;
	static int _answer;
	static List<Character> _operators;
	static List<Integer> _numbers;

	public static void main(String[] args) throws IOException {
		_equation = input.readLine().toCharArray();

		int num = 0;

		_operators = new ArrayList<Character>();
		_numbers = new ArrayList<>();
		for (int i = 0; i < _equation.length; i++) {
			if (_equation[i] >= '0' && _equation[i] <= '9') {
				num++;
			}
			if (_equation[i] == '+' || _equation[i] == '-') {
				_operators.add(_equation[i]);
				int digit = 1;
				int number = 0;
				for (int j = i - 1; j > i - 1 - num; j--) {
					number += digit * (_equation[j] - '0');
					digit *= 10;
				}
				_numbers.add(number);
				num = 0;
			}
			if (i == _equation.length - 1) {
				int digit = 1;
				int number = 0;
				for (int j = i; j > i - num; j--) {
					number += digit * (_equation[j] - '0');
					digit *= 10;
				}
				_numbers.add(number);
			}
		}

		for (int i = 0; i < _operators.size(); i++) {
			if (_operators.get(i) == '-') {
				if (i != _operators.size() - 1) {
					int idx = i + 1;
					int num2 = _numbers.get(idx);
					while (_operators.get(i + 1) == '+') {
						num2 += _numbers.get(i + 2);
						_numbers.set(idx, num2);
						_numbers.set(i + 2, 0);
						if (i == _operators.size() - 2) {
							break;
						}
						i++;
					}
				}
			}
		}
//		System.out.println(Arrays.toString(_numbers.toArray()));

		_answer = _numbers.get(0);
		for (int i = 0; i < _operators.size(); i++) {
			if (_operators.get(i) == '+') {
				_answer += _numbers.get(i + 1);
			} else {
				_answer -= _numbers.get(i + 1);
			}
		}

		output.append(_answer);
		System.out.print(output);
	}

}
