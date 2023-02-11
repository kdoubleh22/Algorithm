import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws Exception {
		int[] alphabets = { 3, 2, 1, 2, 3 // ABCDE
				, 3, 2, 3, 3, 2 // FGHIJ
				, 2, 1, 2, 2, 1 // KLMNO
				, 2, 2, 2, 1, 2 // PQRST
				, 1, 1, 1, 2, 2 // UVWXY
				, 1 }; // Z

		String A = input.readLine();
		String B = input.readLine();

		int[] intA = new int[A.length()];
		int[] intB = new int[B.length()];
		for (int i = 0; i < A.length(); i++) {
			intA[i] = alphabets[A.charAt(i) - 'A'];
			intB[i] = alphabets[B.charAt(i) - 'A'];
		}

		int[] aMixB = new int[A.length() + B.length()];
		for (int i = 0; i < A.length(); i++) {
			aMixB[2 * i] = intA[i];
			aMixB[2 * i + 1] = intB[i];
		}

		int length = A.length() + B.length();
		while (length > 2) {
			for (int i = 0; i < length - 1; i++) {
				aMixB[i] = (aMixB[i] + aMixB[i + 1]) % 10;
			}
			length -= 1;
		}

		output.append(aMixB[0]).append(aMixB[1]);

		System.out.println(output);
	}

}