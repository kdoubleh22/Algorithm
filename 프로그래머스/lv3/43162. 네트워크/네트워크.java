import java.util.*;

/*
union-find로 몇 덩어린지 세면 될거같은데.
*/

class Solution {
    static int[] _parent;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        // 초기화.
        _parent = new int[n];
        for(int i=0; i<n; i++){
            _parent[i] = i;
        }
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i!=j){
                    if(computers[i][j] == 1){
                        union(i,j);
                        // System.out.println("i:"+i+" j:"+j);
                        // System.out.println(Arrays.toString(_parent));
                    }
                }
            }
        }
        
        for(int i=0; i<n; i++){
            find(i);
        }
        
        HashSet<Integer> hs = new HashSet<>();
        for(int i : _parent){
            if(!hs.contains(i)){
                answer++;
                hs.add(i);
            }
        }
        
        return answer;
    }
    
    static void union(int a, int b){
        int ar = find(a);
        int br = find(b);
        
        if(ar != br){
            if(ar > br){
                _parent[ar]=br;
            }
            else{
                _parent[br]=ar;
            }
        }
    }
    
    static int find(int child){
        int p = _parent[child];
        
        if(p == child){
            return p;
        }
        
        return _parent[child] = find(p);
    }
}