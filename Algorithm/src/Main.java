import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[] visited;
	static long answer = 1;
	static ArrayList<Integer>[] g;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		g = new ArrayList[N + 1];

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			if (g[from] == null) {
				g[from] = new ArrayList<>();
			}
			g[from].add(to);

			if (g[to] == null) {
				g[to] = new ArrayList<>();
			}
			g[to].add(from);
		}

//		System.out.println(Arrays.toString(g));

		visited = new boolean[N + 1];

		for (int i = 1; i < N + 1; i++) {
			if (!visited[i] && g[i] != null) {
				int count = dfs(i);
				answer = (answer * count) % 1000000007;
			}
		}
		System.out.println(answer);
	}

	private static int dfs(int i) {
		int nodeCount = 1;

		// 1. 할일 처리(ex: 방문 처리)
		visited[i] = true;

		// 2. 자식 탐색
		List<Integer> childs = g[i];
		for (Integer c : childs) {
			if (!visited[c]) {
//        		visited[c] = true;
				nodeCount += dfs(c);
			}
		}

		return nodeCount;

	}
}