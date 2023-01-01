package swea.d3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author me
 * @see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14w-rKAHACFAYD
 * @performance 18,456 kb 108 ms
 * @category 
 * @memo ArrayList는 끝에서 데이터 삽입 삭제 일어날 때. LinkedList는 중간에서 삽입 삭제 일어날 때. 상황에 맞게 사용. 제출할 때, file로 input받은거 주석처리. 
 */

public class SWEA_D3_01228 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int n, instNum;
	static List<Integer> cryptograms;

	public static void main(String[] args) throws NumberFormatException, IOException {
//		input = new BufferedReader(new FileReader("swea_01228_input.txt"));// d

		for (int tc = 1; tc <= 10; tc++) {
			n = Integer.parseInt(input.readLine());
			tokens = new StringTokenizer(input.readLine());
			cryptograms = new LinkedList<Integer>();
			for (int i = 0; i < 10; i++) {
				cryptograms.add(Integer.parseInt(tokens.nextToken()));
			}
			instNum = Integer.parseInt(input.readLine());
			tokens = new StringTokenizer(input.readLine());
			for (int i = 0; i < instNum; i++) {
				tokens.nextToken();
				int x = Integer.parseInt(tokens.nextToken());
				int y = Integer.parseInt(tokens.nextToken());
				for (int j = x; j < x + y; j++) {
					if (j < 10) {
						cryptograms.add(j, Integer.parseInt(tokens.nextToken()));
						cryptograms.remove(10);
					} else {
						tokens.nextToken();
					}
				}
			}
			output.append("#").append(tc).append(" ");
			for (int i = 0; i < 10; i++) {
				output.append(cryptograms.get(i)).append(" ");
			}
			output.append("\n");
		}
		System.out.print(output);
	}

}