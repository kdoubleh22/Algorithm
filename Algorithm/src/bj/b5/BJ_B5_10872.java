package bj.b5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B5_10872 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens;
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(input.readLine());
        int answer = 1;
        for(int i=1;i<=n;i++){
            answer*=i;
        }
        System.out.println(answer);
    }
}
