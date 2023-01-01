package ct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class 최대로연속한숫자 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	
	static int _n,_m;
	static TreeSet<Integer> _nums;
	static TreeSet<Integer> _results;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n=Integer.parseInt(tokens.nextToken());
		_m=Integer.parseInt(tokens.nextToken());

		_nums = new TreeSet<>();
		for(int i=0;i<=_n;i++) {
			_nums.add(i);
		}
		
		int min = 0;
		int max = _n;
		int ml=_n+1; // max length
		
		tokens = new StringTokenizer(input.readLine());
		while(_m-->0) {
			int del = Integer.parseInt(tokens.nextToken());
			
			if(del>=min && del<=max) {
				
			}
		}
		
	}

}
