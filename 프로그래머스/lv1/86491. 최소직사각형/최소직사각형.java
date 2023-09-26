import java.io.*;
import java.util.*;

/*
명함은 가로가 더 길다고 가정.
그럼 가로끼리 큰걸로. 세로끼리 큰걸로. 끝.
*/

class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        
        int width = -1;
        int height = -1;
        
        for(int[] size : sizes){
            int w = Math.max(size[0], size[1]);
            int h = Math.min(size[0], size[1]);
            
            width = Math.max(width,w);
            height = Math.max(height,h);
        }
        
        return width * height;
    }
}