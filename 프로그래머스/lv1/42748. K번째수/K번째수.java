import java.io.*;
import java.util.*;

/*
정렬, 조회
*/

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for(int tc=0; tc<commands.length; tc++){
            int[] command = commands[tc];
            
            int i = command[0];
            int j = command[1];
            int k = command[2];
            
            int[] temp = new int[j-i+1];
            
            for(int m = i-1; m <j; m++){
                temp[m-i+1] = array[m];
            }
            
            // 정렬.
            Arrays.sort(temp);
            
            // 조회.
            int localAns = temp[k-1];
            
            answer[tc] = localAns;
        }
        
        return answer;
    }
}