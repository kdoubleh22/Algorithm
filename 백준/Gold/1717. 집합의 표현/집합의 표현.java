import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, m;
	static int[] parent, rank;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		initialize();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (op == 0) {
				union(a, b);
			} else { // op == 1
				if (find(a) == find(b)) {
					sb.append("YES\n");
				} else {
					sb.append("NO\n");
				}
			}
		}

		System.out.println(sb);
	}

	static void initialize() {
		parent = new int[n + 1];
		rank = new int[n + 1];

		for (int x = 0; x <= n; x++) {
			parent[x] = x;
			rank[x] = 0;
		}
	}

	static int find(int x) {
		if (x == parent[x]) {
			return x;
		}

		return parent[x] = find(parent[x]);
	}

	static void union(int a, int b) {
		int A = find(a);
		int B = find(b);

		if (A == B) {
			return;
		}

		if (rank[A] < rank[B]) {
			parent[A] = B;
		} else if (rank[A] > rank[B]) {
			parent[B] = A;
		} else { // rank[A] == rank[B]
			parent[B] = A;
			rank[A]++;
		}
	}
}