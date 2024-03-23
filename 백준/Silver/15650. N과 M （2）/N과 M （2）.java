import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N;

	public static void main(String[] args) throws Exception{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		comb(0, 1, new int[M]);
		
		System.out.println(sb);
	}

	static void comb(int cnt, int start, int[] result) {
		if (cnt == result.length) {
			for (int i : result) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			
			return;
		}
		
		for (int i = start; i <= N; i++) {
			result[cnt] = i;
			comb(cnt + 1, i + 1, result);
		}
	}
}