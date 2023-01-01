package bj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BJ_S5_01181 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		TreeSet<Word> ts = new TreeSet<>();

		while (_n-- > 0) {
			ts.add(new Word(input.readLine()));
		}

		for (Word w : ts) {
			output.append(w.word).append("\n");
		}

		System.out.println(output);

	}

	static class Word implements Comparable<Word> {
		String word;

		public Word(String word) {
			super();
			this.word = word;
		}

		@Override
		public int compareTo(Word o) {
			if (this.word.length() == o.word.length()) {
				return this.word.compareTo(o.word);
			} else {
				return Integer.compare(this.word.length(), o.word.length());
			}
		}

	}

}
