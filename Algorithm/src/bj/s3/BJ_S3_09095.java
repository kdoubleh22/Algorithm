package bj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1-1 1개
 * 2-11 2 2개
 * 3-111 12 21 3 4개
 * 4-1111 211 121 112 22 13 31 7개
 * 5- 1+2+4+7=14
 * 6- 1+2+4+7+14=27
 * 7 - 44
 *  A[i]=A[1]+...A[i-1]
 *  A[i+1]=A[
 */

public class BJ_S3_09095 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	
	static int _n;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		_n=Integer.parseInt(input.readLine());
		
		for(int i=0;i<_n;i++) {
			
		}
		
		System.out.println(output);
	}

}
