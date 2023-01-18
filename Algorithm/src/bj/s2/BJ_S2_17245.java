package bj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S2_17245 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens;

    static int _n;
    static int[] _heights;
    static int _on;
    static long _total;
    public static void main(String[] args) throws IOException {
        _n=Integer.parseInt(input.readLine());

        _heights=new int[10000001];
        _total=0;
        for(int i=0;i<_n;i++){
            tokens = new StringTokenizer(input.readLine());
            for(int j=0;j<_n;j++){
                _heights[Integer.parseInt(tokens.nextToken())]+=1;

            }
        }

        _on=0;
        for(int t=0;t<10000001;t++){
            _on+=_heights[t];
//            if(_on>=)
        }

    }
}
