import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        // 찍는 방식. pick
        int[] p1 = {1,2,3,4,5};
        int[] p2 = {2,1,2,3,2,4,2,5};
        int[] p3 = {3,3,1,1,2,2,4,4,5,5};
        
        // 맞춘개수. hit
        int h1 = 0;
        int h2 = 0;
        int h3 = 0;
        
        for(int i=0; i<answers.length; i++){
            if(p1[i%5] == answers[i]){
                h1++;
            }
            if(p2[i%8] == answers[i]){
                h2++;
            }
            if(p3[i%10] == answers[i]){
                h3++;
            }
        }
        
        List<Integer> answerList = new ArrayList<>();
        
        // 가장 높은 점수 찾기.
        int highest = Math.max(Math.max(h1,h2),h3);
        
        // 가장 높은 점수인 애들 넣어주기.
        if(h1 == highest){
            answerList.add(1);
        }
        if(h2 == highest){
            answerList.add(2);
        }
        if(h3 == highest){
            answerList.add(3);
        }
        
        int[] answer = new int[answerList.size()];
        
        for(int i=0; i<answer.length; i++){
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
}