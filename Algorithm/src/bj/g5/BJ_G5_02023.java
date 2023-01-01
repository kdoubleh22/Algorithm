package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author me
 * @see https://www.acmicpc.net/problem/2023
 * @performance 11564 KB 84 ms
 * @category #수학 #정수론 #백트래킹 #소수 판정
 * @memo List를 담는 List를 만드려면, List<List<Type>> = new ArrayList<List<Type>>();
 *       첫번째것만 ArrayList로 선언하면됨. 소인수는 대칭이므로 root(n)까지 나눠보면 된다.
 *       배열에도 List를 담을 수 있다. 대신 type parameter<>를 안 씀. List[] array = new List[4];
 *       math.sqrt보다 그냥 제곱으로 쓰는게 더 빠르다.
 *       에라토스테네스의 체 미리 배열을 선언해놓고, 하나씩 지움. 숫자가 크면 안 될것같다.
 * 
 */

public class BJ_G5_02023 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int n;

	static boolean isPrime(int number) {
		for (int i = 2; i*i <= number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		n = Integer.parseInt(input.readLine());
		List<List<Integer>> numsOfDigits = new ArrayList<List<Integer>>();
		for (int i = 0; i < n; i++) {
			numsOfDigits.add(new ArrayList<Integer>());
		}
//		System.out.println("size of numsofDigits: " + numsOfDigits.size());//d
		numsOfDigits.get(0).add(2);
		numsOfDigits.get(0).add(3);
		numsOfDigits.get(0).add(5);
		numsOfDigits.get(0).add(7);
//		System.out.println("numsOfDigits:" + numsOfDigits.get(0).toString());// d

		int idx = 1; // 넣을 index. 이미 0은 채워짐.
		while (true) {
			if (idx == n) {
				for (int i = 0; i < numsOfDigits.get(idx - 1).size(); i++) {
					output.append(numsOfDigits.get(idx - 1).get(i)).append("\n");
				}
				break;
			}
			List<Integer> previous = numsOfDigits.get(idx - 1);
			for (int i = 0; i < previous.size(); i++) {
				for (int j = 1; j <= 9; j += 2) {
					int newNum = previous.get(i) * 10 + j; // 짝수는 소수가 될 수없음.
					if (isPrime(newNum)) {
						numsOfDigits.get(idx).add(newNum);
					}
				}
			}
			idx++;
		}
		System.out.print(output);
	}

}
