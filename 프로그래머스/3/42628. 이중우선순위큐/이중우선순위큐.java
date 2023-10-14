import java.io.*;
import java.util.*;

/*
TreeSet
*/

class Solution {
    static StringTokenizer tokens;
    
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        for(String operation : operations){
            tokens = new StringTokenizer(operation);
            
            String s = tokens.nextToken();
            int i = Integer.parseInt(tokens.nextToken());
            
            if(s.equals("I")){
                minHeap.add(i);
                maxHeap.add(i);
            }
            // D
            else{
                if(!minHeap.isEmpty() && !maxHeap.isEmpty()){
                    if(i==1){
                        int max = maxHeap.poll();
                        minHeap.remove(max);
                    }
                    // i==-1
                    else{
                        int min = minHeap.poll();
                        maxHeap.remove(min);
                    }
                }
            }
        }
        
        if(!minHeap.isEmpty() && !maxHeap.isEmpty()){
            answer[0] = maxHeap.poll();
            answer[1] = minHeap.poll();
        }
        
        return answer;
    }
}