package ct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 숫자가가장큰인접한곳으로동시에이동 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	
	static int[][] deltas= {{-1,0},{1,0},{0,-1},{0,1}};
	
	static int _n,_m,_t;
	static int[][] _map,_count,_nc; // nc:next count
	
	static boolean isIn(int r,int c) {
		return r>=0 && r<_n && c>=0 && c<_n;
	}
	
	static void delete() {
		for(int i=0;i<_n;i++) {
			for(int j=0;j<_n;j++) {
				if(_nc[i][j]>=2) {
					_nc[i][j]=0;
				}
			}
		}
	}
	
	static void move() {
		_nc = new int[_n][_n];
		for(int i=0;i<_n;i++) {
			for(int j=0;j<_n;j++) {
				// 구슬이 있으면
				if(_count[i][j]==1) {
					int max = Integer.MIN_VALUE;
					int maxI=-1;
					int maxJ=-1;
					for(int d=0;d<4;d++) {
						int ni = i+deltas[d][0];
						int nj= j +deltas[d][1];
						if(isIn(ni,nj)&&_map[ni][nj]>max) {
							max=_map[ni][nj];
							maxI=ni;
							maxJ=nj;
						}
					}
					_nc[maxI][maxJ]+=1;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		_n=Integer.parseInt(tokens.nextToken());
		_m=Integer.parseInt(tokens.nextToken());
		_t=Integer.parseInt(tokens.nextToken());
		
		_map = new int[_n][_n];
		for(int i=0;i<_n;i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j=0;j<_n;j++) {
				_map[i][j]=Integer.parseInt(tokens.nextToken());
			}
		}
		
		_count = new int[_n][_n];
		for(int i=0;i<_m;i++) {
			tokens=new StringTokenizer(input.readLine());
			int r = Integer.parseInt(tokens.nextToken())-1;
			int c = Integer.parseInt(tokens.nextToken())-1;
			_count[r][c] = 1;
		}
		
		for(int t=1;t<=_t;t++) {
			// 움직이고,
			move(); 
			// 2이상이면 삭제
			delete();
			// nc를 count에 복사
			for(int i=0;i<_n;i++) {
				_count[i]=Arrays.copyOf(_nc[i], _n);
			}
		}
		
		int answer =0;
		for(int i=0;i<_n;i++) {
			for(int j=0;j<_n;j++) {
				if(_count[i][j]!=0) {
					answer++;
				}
			}
		}
		
		output.append(answer);
		System.out.println(output);
		

	}

}
