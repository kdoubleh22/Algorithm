import java.io.*;
import java.util.*;

/*
2^20 = 백만정도. 다 해보면 될듯. 부분집합.
*/

class Solution {
    static int _len;
    static int _answer;
    static int[] _numbers;
    static int _target; 
    
    public int solution(int[] numbers, int target) {
        // 1.
//         _len = numbers.length;
//         _answer = 0;
//         _numbers = numbers;
//         _target = target;
        
//         powerSet(0,new int[_len]);
        
//         return _answer;
        
        // 2.
        int answer = dfs(numbers,target,0,0);
        return answer;
    }
    
    // 1.
    static void powerSet(int cnt, int[] result){
        if(cnt == _len){
            int res = 0;
            for(int i=0; i<result.length; i++){
                if(result[i] == 0){
                    res += -1 * _numbers[i];
                }
                else{
                    res += 1 * _numbers[i];
                }
            }
            
            // 결과가 타겟이랑 같으면 answer +1.
            if(res == _target){
                _answer++;
            }
            
            return;
        }
        
        result[cnt]=0;
        powerSet(cnt+1,result);
        result[cnt]=1;
        powerSet(cnt+1,result);
    }
    
    // 2.
    static int dfs(int[] numbers, int target, int cnt, int sum){
        if(cnt == numbers.length){
            if(sum == target){
                return 1;
            }
            else{
                return 0;
            }
        }
        
        return dfs(numbers,target,cnt+1,sum+numbers[cnt]) + dfs(numbers,target,cnt+1,sum-numbers[cnt]);
    }
}