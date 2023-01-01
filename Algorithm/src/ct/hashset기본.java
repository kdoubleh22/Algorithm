package ct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class hashset기본 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int n = Integer.parseInt(input.readLine());
		
		Set<Integer> hs = new HashSet<>();
		
		while(n-->0) {
			tokens = new StringTokenizer(input.readLine());
			String cmd = tokens.nextToken();
			int num = Integer.parseInt(tokens.nextToken());
			
			
			if(cmd.equals("add")) {
				hs.add(num);
			}else if(cmd.equals("remove")) {
				hs.remove(num);
			}else if(cmd.equals("find")) {
				if(hs.contains(num)) {
					output.append("true");
				}else {
					output.append("false");
				}
				output.append("\n");
			}
		}
		
		System.out.println(output);

	}

}
