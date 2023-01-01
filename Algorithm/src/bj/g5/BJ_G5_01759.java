package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G5_01759 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _l, _c;
	static int[] _alphabets;

	static boolean isVowel(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}

	static void password(int cnt, int start, int vowels, int consonants, int[] result) {
		if (cnt == _l) {
			if (vowels >= 1 && consonants >= 2) {
				for (int c : result) {
					output.append((char) (c + 'a'));
				}
				output.append("\n");
			}
			return;
		}

		for (int i = start; i < _c; i++) {
			result[cnt] = _alphabets[i];
			if (isVowel((char) (_alphabets[i] + 'a'))) {
				password(cnt + 1, i + 1, vowels + 1, consonants, result);
			} else {
				password(cnt + 1, i + 1, vowels, consonants + 1, result);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_l = Integer.parseInt(tokens.nextToken());
		_c = Integer.parseInt(tokens.nextToken());

		_alphabets = new int[_c];
		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < _c; i++) {
			_alphabets[i] = tokens.nextToken().charAt(0) - 'a';
		}
		Arrays.sort(_alphabets);

		password(0, 0, 0, 0, new int[_l]);

		System.out.println(output);
	}

}
