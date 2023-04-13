import java.io.*;
import java.util.*;

class Solution {
    static int[][] deltas = {{-1,0},{0,1},{1,0},{0,-1}};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        int aIdx=0;
        for(String[] place : places){
            char[][] placeArr = new char[5][];
            for(int r=0;r<5;r++){
                placeArr[r]=place[r].toCharArray();
            }
            
            // 입력 확인.
            // for(char[] row: placeArr){
            //     System.out.println(Arrays.toString(row));
            // }
            
            answer[aIdx]=keep(placeArr);
            aIdx+=1;
        }
        
        return answer;
    }
    
    static boolean isIn(int r,int c){
        return 0<=r && r<5 && 0<=c && c<5;
    }
    
    static int keep(char[][] placeArr){
        int result = 1; // 성공
        
        // bfs 2이하까지만 돌면서, p있는지 확인
        for(int r=0;r<5;r++){
            for(int c=0;c<5;c++){
                if(placeArr[r][c]=='P'){
                    boolean[][] visited = new boolean[5][5];
                    ArrayDeque<Point> q = new ArrayDeque<>();

                    q.add(new Point(r,c));
                    visited[r][c]=true;

                    int breadth = -1;
                    while(!q.isEmpty()){
                        breadth+=1;
                        if(breadth==3){
                            break;
                        }
                        int size = q.size();
                        for(int i=0;i<size;i++){
                            Point cur = q.poll();
                            int cr = cur.x;
                            int cc = cur.y;
                            if(breadth!=0 && placeArr[cr][cc]=='P'){
                                return 0;
                            }
                            
                            for(int d=0;d<4;d++){
                                int nr = cr + deltas[d][0];
                                int nc = cc + deltas[d][1];
                                
                                if(isIn(nr,nc)&&!visited[nr][nc]&&placeArr[nr][nc]!='X'){
                                    q.add(new Point(nr,nc));
                                    visited[nr][nc]=true;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return result;
    }
    
    static int dist(int r1,int c1,int r2,int c2){
        return Math.abs(r1-r2)+Math.abs(c1-c2);
    }
    
    static class Point{
        int x;
        int y;
        
        public Point(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
}