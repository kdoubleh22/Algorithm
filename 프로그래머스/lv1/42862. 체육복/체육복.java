import java.io.*;
import java.util.*;

/*
lost를 돌면서, reserve에서 있는지 확인하고, 없으면 앞에서부터 빌려줌.
*/

class Solution {
    
    
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n;
        
        HashSet<Integer> hs = new HashSet<>();
        
        for(int r : reserve){
            hs.add(r);
        }
        
        TreeSet<Integer> ts = new TreeSet<>();
        
        for(int l : lost){
            ts.add(l);
        }
        
        // 여벌 체육복을 가져온 학생이 체육복을 도난당함.
        // 1.
        // for(int l : lost){
        //     if(hs.contains(l)){
        //         hs.remove(l);
        //         ts.remove(l);
        //     }
        // }
        
        // 2.
        for(Iterator<Integer> iterator = ts.iterator(); iterator.hasNext(); ){
            int i = iterator.next();
            
            if(hs.contains(i)){
                hs.remove(i);
                iterator.remove();
            }
        }
        
        for(int l : ts){
                // 먼저 앞 번호 확인.
                if(hs.contains(l-1)){
                    hs.remove(l-1);
                }
                // 뒷 번호 확인.
                else if(hs.contains(l+1)){
                    hs.remove(l+1);
                }
                // 앞 뒷 번호 둘 다 여벌 체육복이 없는 경우.
                else{
                    answer--;
                }
        }
        
        return answer;
    }
}