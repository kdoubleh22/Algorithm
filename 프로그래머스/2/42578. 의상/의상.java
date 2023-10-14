import java.io.*;
import java.util.*;

/*
(각 의상의 종류별 개수 + 1)을 곱하고 다 없는 경우 -1.
HashMap
*/

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        HashMap<String,Integer> hm = new HashMap<>();
        for(String[] sArr : clothes){
            String name = sArr[0];
            String type = sArr[1];
            
            // 처음 보는 종류.
            if(!hm.containsKey(type)){
                hm.put(type,1);
            }
            // 이미 봤던 종류면 +1.
            else{
                hm.put(type, hm.get(type)+1);
            }
        }
        
        for(String key : hm.keySet()){
            answer *= hm.get(key) + 1;
        }
        
        answer = answer - 1;
        
        return answer;
    }
}