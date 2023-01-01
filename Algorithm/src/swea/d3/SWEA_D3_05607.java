package swea.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @ref https://st-lab.tistory.com/241
 *
 */

public class SWEA_D3_05607 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _t;
	static long _p = 1234567891l;

	static long nFactModP(long n) {
		long result = 1l;

		while (n > 1) {
			result = (result * n) % _p;
			n--;
		}

		return result;
	}

	static long baseExponentModP(long base, long exponent) {
		if (exponent == 1) {
			return base % _p;
		}

		long temp = baseExponentModP(base, exponent / 2);

		if (exponent % 2 == 0) {
			return temp * temp % _p;
		}

		return (temp * temp % _p) * base % _p;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_t = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= _t; tc++) {
			tokens = new StringTokenizer(input.readLine());
			long n = Long.parseLong(tokens.nextToken());
			long r = Long.parseLong(tokens.nextToken());

			long answer = (nFactModP(n)
					* ((baseExponentModP(nFactModP(r), _p - 2) * (baseExponentModP(nFactModP(n - r), _p - 2))) % _p))
					% _p;

			output.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.println(output);

	}

}
