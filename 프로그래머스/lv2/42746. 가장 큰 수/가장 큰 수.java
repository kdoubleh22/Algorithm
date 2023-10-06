import java.io.*;
import java.util.*;

/*
숫자를 문자열로 바꿔서 정렬 후 쭉 이어붙이면 될 듯.
*/

/*
입력이 전부 0인 경우, "000...00"이 아닌 "0"으로 예외처리.
*/

/*
첫글자만 0인지 확인하면 됨. 사전적 내림차순이므로 처음이 0이면 다 0이다.
*/

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        
        String[] strNumbers = new String[numbers.length];
        
        for(int i=0; i<numbers.length; i++){
            strNumbers[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(strNumbers, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2){
                return -1 * (o1+o2).compareTo(o2+o1);
            }
        });
        
        for(String strNumber : strNumbers){
            sb.append(strNumber);
        }
        
        answer = sb.toString();
        
//         boolean flag = true;
//         for(int i=0; i<answer.length(); i++){
//             if(answer.charAt(i) != '0'){
//                 flag = false;
//                 break;
//             }
//         }
            
//         if(flag){
//             answer = "0";
//         }
        
        if(answer.charAt(0)=='0'){
            answer="0";
        }
        
        return answer;
    }
}