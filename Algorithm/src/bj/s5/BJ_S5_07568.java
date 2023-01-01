package bj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S5_07568 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static Body[] _bodies;
	static int[] _answers;

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_bodies = new Body[_n];
		for (int i = 0; i < _n; i++) {
			tokens = new StringTokenizer(input.readLine());
			int w = Integer.parseInt(tokens.nextToken());
			int h = Integer.parseInt(tokens.nextToken());
			_bodies[i] = new Body(w, h);
		}

		_answers = new int[_n];
		for (int i = 0; i < _n; i++) {
			int answer = 1;
			for (int j = 0; j < _n; j++) {
				if (i == j) {
					continue;
				}
				if (_bodies[i].weight < _bodies[j].weight && _bodies[i].height < _bodies[j].height) {
					answer++;
				}
			}
			_answers[i] = answer;
		}

		for (int i = 0; i < _n; i++) {
			output.append(_answers[i]).append(" ");
		}

		System.out.println(output);
	}

	static class Body {
		int weight, height;

		public Body(int weight, int height) {
			this.weight = weight;
			this.height = height;
		}

	}
}
