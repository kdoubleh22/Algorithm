import java.util.*;

/*
이전 값(prev)이 아니면 ArrayDeque 추가하는 방식.
*/

public class Solution {
    public int[] solution(int []arr) {
        int[] answer = {};
        
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        
        int prev = -1;
        for(int num : arr){
            // 이전값이 아니면,
            if(num != prev){
                // deque에 추가, prev update.
                deque.addLast(num);
                prev = num;
            }
            // 이전 값이면 무시.
        }
        
        answer = new int[deque.size()];
        
        int idx = 0;
        while(!deque.isEmpty()){
            answer[idx++] = deque.removeFirst();
        }

        return answer;
    }
}