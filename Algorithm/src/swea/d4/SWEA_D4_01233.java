package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D4_01233 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int N;
	static String[] arr;
	static boolean flag;

	static void dfs(int nodeNum) {

		if (nodeNum > N) {
			return;
		}

		if (arr[nodeNum].equals("+") || arr[nodeNum].equals("-") || arr[nodeNum].equals("*")
				|| arr[nodeNum].equals("/")) {
			dfs(nodeNum * 2);
			dfs(nodeNum * 2 + 1);

		} else { // 본인이 숫자이면,
			if (2 * nodeNum <= N) { // 자식 노드가 있으면,
				flag = true;
				return;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
//		input = new BufferedReader(new FileReader("./swea01233input.txt"));//d

		for (int tc = 1; tc <= 10; tc++) {
			N = Integer.parseInt(input.readLine());

			arr = new String[N + 1];
			for (int i = 1; i <= N; i++) {
				tokens = new StringTokenizer(input.readLine());
				tokens.nextToken();
				arr[i] = tokens.nextToken();
			}
			output.append("#").append(tc).append(" ");

			flag = false;
			dfs(1);

			if (flag) {
				output.append("0");
			} else {
				output.append("1");
			}

			output.append("\n");
		}
		System.out.print(output);
	}

}
