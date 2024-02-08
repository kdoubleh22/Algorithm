import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		long[] arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int treeSize = 1 << (h + 1);
		long[] tree = new long[treeSize];
		init(arr, tree, 1, 0, N - 1);

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if (a == 1) {
				update(arr, tree, 1, 0, N - 1, b - 1, c);
			} else {
				sb.append(query(tree, 1, 0, N - 1, b - 1, (int) (c - 1))).append("\n");
			}
		}

		System.out.println(sb);
	}

	static void init(long[] a, long[] tree, int node, int start, int end) {
		if (start == end) {
			tree[node] = a[start];
			return;
		}

		init(a, tree, node * 2, start, (start + end) / 2);
		init(a, tree, node * 2 + 1, (start + end) / 2 + 1, end);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	static long query(long[] tree, int node, int start, int end, int left, int right) {
		if (left > end || right < start) {
			return 0;
		}
		if (left <= start && end <= right) {
			return tree[node];
		}

		long lsum = query(tree, node * 2, start, (start + end) / 2, left, right);
		long rsum = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		return lsum + rsum;
	}

	static void update(long[] a, long[] tree, int node, int start, int end, int index, long val) {
		if (index < start || index > end) {
			return;
		}
		if (start == end) {
			a[index] = val;
			tree[node] = val;
			return;
		}

		update(a, tree, node * 2, start, (start + end) / 2, index, val);
		update(a, tree, node * 2 + 1, (start + end) / 2 + 1, end, index, val);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
}