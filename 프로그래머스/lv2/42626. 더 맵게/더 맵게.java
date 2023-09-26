import java.io.*;
import java.util.*;

/*
양쪽 끝에서만 추가/삭제 -> deque.
정렬 -> priority queue.
근데 linked list로도 비슷할 것 같음.
*/

class Solution {
    public int solution(int[] scovilles, int K) {
        int answer = 0;
        
//         // LinkedList 버전. -> 시간초과
//         LinkedList<Integer> foods = new LinkedList<>();
        
//         for(int scoville : scovilles){
//             foods.add(scoville);
//         }
        
//         // 정렬.
//         Collections.sort(foods);
        
//         while(true){
//             if( foods.get(0) >= K ){
//                 break;
//             }
            
//             int first = foods.remove(0);
//             int second = foods.remove(0);
            
//             // 섞기.
//             foods.add(first + (2 * second));
            
//             Collections.sort(foods);
            
//             // 만들 수 없는 경우.
//             if( foods.size() == 1 && foods.get(0) < K){
//                 answer= -1;
//                 break;
//             }
            
//             // System.out.println(foods);
            
//             answer++;
//         }
        
        // priority queue 버전.
        PriorityQueue<Integer> foods = new PriorityQueue<>();
        
        for(int scoville : scovilles){
            foods.add(scoville);
        }
        
        while(true){
            int first = foods.remove();
            
            if(first >= K){
                break;
            }
            
            int second = foods. remove();
            
            foods.add(first + (2 * second));
            
            // 할 수 없는 경우.
            if(foods.size() == 1 && foods.peek() < K){
                answer = -1;
                break;
            }
            
            answer++;
        }
        
        return answer;
    }
}