import java.util.*;

class Solution {
    
    static int answer;
    static int[] parent;
    static int[] rank;
    
    public int solution(int n, int[][] computers) {
        answer = n;
        
        parent = new int[n];
        for (int i = 0; i < n; i++){
            parent[i] = i;
        }
        rank = new int[n];
        
        for (int i = 0; i < n; i++){
            for (int j = i + 1;j < n;j++){
                if (computers[i][j] == 1){
                    union(i,j);
                }
            }
        }
        
        return answer;
    }
    
    int find(int x){
        if (x == parent[x]){
            return x;
        }
        
        return parent[x] = find(parent[x]);
    }
    
    void union(int a, int b){
        int A = find(a);
        int B = find(b);
        
        if (A == B) {
            return;
        }
        
        answer--;
        
        if (rank[A] < rank[B]){
            parent[A] = B;
        } else if(rank[A] > rank[B]){
            parent[B] = A;
        } else{
            parent[B] = A;
            rank[A]++;
        }
    }
}