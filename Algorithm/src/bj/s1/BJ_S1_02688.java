import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder output= new StringBuilder();
  static StringTokenizer tokens;

  static int _t;
  static long[][] _arr;

  public static void main(String[] args) throws IOException{
    _t=Integer.parseInt(input.readLine());
    
    _arr = new long[65][11];
    for(int i=0;i<10;i++){
      _arr[1][i]=1;
    }
    _arr[1][10]=10;
    
    for(int n=2;n<=64;n++){
      long sum=0;
      long num=_arr[n-1][10];
      for(int i=0;i<10;i++){
        _arr[n][i]=num;
        sum+=num;
        num-=_arr[n-1][i];
      }
      _arr[n][10]=sum;
    }

    

    while(_t-->0){
      int n = Integer.parseInt(input.readLine());
      output.append(_arr[n][10]).append("\n");
    }

    System.out.println(output);
  }
} 