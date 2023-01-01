package bj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_S4_01158 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int n, k, i;
	static List<Integer> people;

	static int findNext() {
		i = (i + (k - 1)) % people.size();
		return i;
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		n = Integer.parseInt(tokens.nextToken());
		k = Integer.parseInt(tokens.nextToken());

		people = new LinkedList<Integer>();

		for (int i = 1; i <= n; i++) {
			people.add(i);
		}

		output.append("<");

		i = 0;
		while (true) {
//			System.out.println("i:" + i);// d
			int removedIdx = findNext();
//			System.out.println("removedIdx:" + removedIdx);// d
			output.append(people.remove(removedIdx)).append(", ");
			if (people.size() == 0) {
				break;
			}
		}

		output.delete(output.length() - 2, output.length());
		output.append(">");
		System.out.print(output);

	}

}
