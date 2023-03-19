import java.io.*;
import java.util.*;

class Solution {
    // 거리를 계산하는 함수
    static int calDist(int[] location1, int[] location2){
        return Math.abs(location1[0]-location2[0])+Math.abs(location1[1]-location2[1]);
    }
    
    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();
        
        // 각 번호의 위치를 담은 변수
        int[][] locations = new int[][] {{3,1},{0,0},{0,1},{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2}};
        
        // 왼손과 오른손의 위치를 나타내는 변수
        int[] left = new int[] {3,0};
        int[] right = new int[] {3,2};
        
        // numbers를 순회하면서
        // 1,4,7이면 answer에 left를 추가,
        // 3,6,9면 answer에 right를 추가,
        // 2,5,8,0이면 거리를 계산하여 추가
        // 위치 업데이트.
        for(int number:numbers){
            // 1,4,7
            if(number%3==1){
                answer.append("L");
                left[0]=locations[number][0];
                left[1]=locations[number][1];
            }
            // 3,6,9
            else if(number!=0 && number%3==0){
                answer.append("R");
                right[0]=locations[number][0];
                right[1]=locations[number][1];
            }
            // 2,5,8,0
            else{
                // 왼손과의 거리, 오른손과의 거리
                int leftDist = calDist(left,locations[number]);
                int rightDist = calDist(right,locations[number]);
                // 왼손이 가깝다면, 정답에 왼손을 추가하고, 왼손 위치 업데이트.
                if(leftDist<rightDist){
                    answer.append("L");
                    left[0]=locations[number][0];
                    left[1]=locations[number][1];
                }
                // 오른손이 가깝다면, 정답에 오른손을 추가하고, 오른손 위치 업데이트.
                else if(rightDist<leftDist){
                    answer.append("R");
                    right[0]=locations[number][0];
                    right[1]=locations[number][1];
                }
                // 거리가 같다면, 왼손잡이인지 오른손잡이인지 확인 후 해당 손 위치 업데이트.
                else{
                    // 왼손잡이라면,
                    if(hand.equals("left")){
                        answer.append("L");
                        left[0]=locations[number][0];
                        left[1]=locations[number][1];
                    }
                    // 오른손잡이라면,
                    else{
                        answer.append("R");
                        right[0]=locations[number][0];
                        right[1]=locations[number][1];
                    }
                }
            }
        }
        
        return answer.toString();
    }
}