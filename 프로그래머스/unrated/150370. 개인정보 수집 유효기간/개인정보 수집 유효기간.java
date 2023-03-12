import java.io.*;
import java.util.*;

/*
-문제
개인정보 n개.
각 개인정보마다 유효기관이 있고, 지나면 파기.
모든 달은 28일까지 있음.
* 유효기간은 date + 28*월 -1일까지.
* 1에는 단위 곱해주면 안 됨.
* 월이 28일까지이므로, 1년도 28*12.
-풀이
terms는  배열에 넣는다.
현재날짜와 privacy의 날짜+유효기관을 비교하여 파기할 정보인지 확인.
날짜를 숫자로 변환하는 함수를 만든다.
*/

class Solution {
    static StringTokenizer tokens;
    
    static int dateToNum(int year,int month,int day){
        return year*28*12 + (month-1)*28 + day;
    }
    
    // terms의 month를 구하는 함수.
    static int findMonth(String[] terms, char type){
        int result = -1;
        
        for(int i=0; i<terms.length; i++){
            tokens = new StringTokenizer(terms[i]," ");
            char t = tokens.nextToken().charAt(0);
            int month = Integer.parseInt(tokens.nextToken());
            if(t==type){
                result = month;
                break;
            }
        }
        
        return result;
    }
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answerList = new ArrayList<>();
        
        tokens = new StringTokenizer(today,".");
        int year = Integer.parseInt(tokens.nextToken());
        int month = Integer.parseInt(tokens.nextToken());
        int day = Integer.parseInt(tokens.nextToken());
        
        int numOfToday = dateToNum(year,month,day);
        
        // privacies를 돌면서
        for(int i=0; i<privacies.length; i++){
            StringTokenizer privacy = new StringTokenizer(privacies[i]," ");
            StringTokenizer date = new StringTokenizer(privacy.nextToken(),".");
            year = Integer.parseInt(date.nextToken());
            month = Integer.parseInt(date.nextToken());
            day = Integer.parseInt(date.nextToken());
            
            char type = privacy.nextToken().charAt(0);
            
            if(numOfToday > (dateToNum(year,month,day) + 28*findMonth(terms,type) - 1)){
                answerList.add(i+1);
            }
        }
        
        System.out.println(answerList);
        
        int[] answer = new int[answerList.size()];
        int idx=0;
        for(int i:answerList){
            answer[idx++]=i;
        }
        
        return answer;
    }
}