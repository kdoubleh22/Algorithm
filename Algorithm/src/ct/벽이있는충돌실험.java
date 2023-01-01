package ct;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class 벽이있는충돌실험 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int _t, _n, _m;
	static List<Bead> _beads, _nb;
	static int[][] _count;

	static boolean isIn(int r, int c) {
		return r >= 0 && r < _n && c >= 0 && c < _n;
	}

	static Bead move2(Bead bead) {
		int r = bead.r;
		int c = bead.c;
		char d = bead.d;
		if (d == 'U') {
			int nr = r + deltas[0][0];
			int nc = c + deltas[0][1];
			if (isIn(nr, nc)) {
				return new Bead(nr, nc, d);
			} else {
				return new Bead(r, c, 'D');
			}
		} else if (d == 'D') {
			int nr = r + deltas[1][0];
			int nc = c + deltas[1][1];
			if (isIn(nr, nc)) {
				return new Bead(nr, nc, d);
			} else {
				return new Bead(r, c, 'U');
			}
		} else if (d == 'L') {
			int nr = r + deltas[2][0];
			int nc = c + deltas[2][1];
			if (isIn(nr, nc)) {
				return new Bead(nr, nc, d);
			} else {
				return new Bead(r, c, 'R');
			}

		} else {
			int nr = r + deltas[3][0];
			int nc = c + deltas[3][1];
			if (isIn(nr, nc)) {
				return new Bead(nr, nc, d);
			} else {
				return new Bead(r, c, 'L');
			}

		}
	}

	static void move() {
		_nb.clear();
		; // next beads
		for (Bead bead : _beads) {
			Bead newBead = move2(bead);
			_count[newBead.r][newBead.c] += 1;
			_nb.add(newBead);
		}
	}

	static void delete() {
		_beads.clear();
		for (Bead b : _nb) {
			if (_count[b.r][b.c] == 1) {
				_beads.add(b);
			}
		}
		for (Bead b : _nb) {
			_count[b.r][b.c] = 0;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		_t = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= _t; tc++) {
			tokens = new StringTokenizer(input.readLine());
			_n = Integer.parseInt(tokens.nextToken());
			_m = Integer.parseInt(tokens.nextToken());

			_beads = new LinkedList<Bead>();
			for (int i = 0; i < _m; i++) {
				tokens = new StringTokenizer(input.readLine());
				int r = Integer.parseInt(tokens.nextToken()) - 1;
				int c = Integer.parseInt(tokens.nextToken()) - 1;
				char d = tokens.nextToken().charAt(0);
				_beads.add(new Bead(r, c, d));
			}

			_count = new int[_n][_n];
			_nb = new LinkedList<>();
			for (int t = 1; t <= 2 * _n; t++) {
				move();
				delete();
			}

			output.append(_beads.size()).append("\n");
		} // tc

		System.out.println(output);

	} // main

	static class Bead {
		int r, c; // row column
		char d; // direction

		public Bead(int r, int c, char d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

	}

}
