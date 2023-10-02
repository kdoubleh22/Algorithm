import java.io.*;
import java.util.*;

/*
몫의 합이 n이 되는 최소 숫자 찾기.
몫의 합이 n이고, 제일 작은 시간의 나머지가 0인 숫자 찾기.
*/

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0l;
        
        long left = 1l;
        long right = 1000000000000000000l;
        while(left < right){
            long mid = (left + right) / 2;
            long res = quotientSum(mid, times);
            
            if(res >= n){
                if(quotientSum(mid-1, times) < n){
                    answer = mid;
                    break;
                }
                right = mid;
            }
            else if (res < n){
                left = mid;
            }
        }
        
        return answer;
    }
    
    static long quotientSum(long t, int[] times){
        long result = 0;
        
        for(int time : times){
            result += t / time;
        }
        
        return result;
    }
}