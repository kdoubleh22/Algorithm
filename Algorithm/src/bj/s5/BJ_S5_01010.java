package bj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * 
 * @author kdoubleh22
 * @see https://www.acmicpc.net/problem/1010
 * @performance
 * @category #
 * @memo int와 long으로 해결 안 되는 매우 큰 정수계산은 BigInteger 사용하기. 팩토리얼은 미리 계산해놓자.
 */

public class BJ_S5_01010 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int T;
	static int[][] nms;
	static BigInteger[] facts = new BigInteger[31];

	static BigInteger factorial(int n) {
		BigInteger result = new BigInteger("1");
		for (int i = n; i >= 1; i--) {
			result = result.multiply(new BigInteger(Integer.toString(i)));
		}
		return result;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(input.readLine());

		nms = new int[T][2];
		for (int i = 0; i < T; i++) {
			tokens = new StringTokenizer(input.readLine());
			nms[i][0] = Integer.parseInt(tokens.nextToken());
			nms[i][1] = Integer.parseInt(tokens.nextToken());
		}

//		for(int[] row:nms) {
//			System.out.println(Arrays.toString(row));
//		}

		facts[0] = new BigInteger("0");
		facts[1] = new BigInteger("1");
		for (int i = 2; i <= 30; i++) {
			facts[i] = facts[i - 1].multiply(new BigInteger(Integer.toString(i)));
		}

		for (int i = 0; i < T; i++) {
			int n = nms[i][1];
			int r = nms[i][0];
			BigInteger result = factorial(n).divide(factorial(r)).divide(factorial(n - r));
			output.append(result).append("\n");
		}

		System.out.print(output);

	}

}
