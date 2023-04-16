import java.io.*;
import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = {};
        
        // 등수를 담는 HashMap
        HashMap<String,Integer> ranks = new HashMap<>();
        
        for(int i=0; i<players.length; i++){
            ranks.put(players[i],i);
        }
        
        // callings를 돌면서
        for(String player : callings){
            // 현재 불린 사람이 몇 등인지 찾음.
            int rank = ranks.get(player);
            
            // 앞 사람 이름.
            String front = players[rank-1];
            
            // 앞 사람과 순서를 바꿔줌.
            players[rank] = front;
            players[rank-1]=player;
            
            // HashMap도 업데이트.
            ranks.put(front,rank);
            ranks.put(player,rank-1);
        }
        
        answer = players;
        
        return answer;
    }
}