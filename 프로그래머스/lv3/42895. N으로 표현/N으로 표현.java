import java.io.*;
import java.util.*;

class Solution {
    public int solution(int N, int number) {
        int answer = -1;
        
        HashSet<Integer>[] array = new HashSet[9];
        
        int cnt = 1;
        
        while( cnt < 9 ){
            array[cnt] = new HashSet<Integer>();
            
            // 부분집합으로 사칙연산.
            for(int i=1; i<cnt; i++){
                for(int j : array[i]){
                    for(int k : array[cnt-i]){
                        array[cnt].add(j+k);
                        array[cnt].add(j-k);
                        array[cnt].add(j*k);
                        if(k!=0){
                            array[cnt].add(j/k);
                        }
                    }
                }
            }
            
            // 연속으로 이어진 수.
            int temp = cnt;
            int nnn = 0; // 연속된 수.
            while(temp-- > 0){
                nnn = nnn*10 + N;
            }
            array[cnt].add(nnn);
            
            if(array[cnt].contains(number)){
                answer = cnt;
                break;
            }
            
            cnt++;
        }
        
        return answer;
    }
}