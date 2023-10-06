import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        ArrayList<Node>[] villages = new ArrayList[N+1];
        for(int i=1; i<N+1; i++){
            villages[i] = new ArrayList<Node>();
        }
        
        for(int[] r : road){
            int a = r[0];
            int b = r[1];
            int c = r[2];
            
            villages[a].add(new Node(b,c));
            villages[b].add(new Node(a,c));
        }
        
        int[] minDists = new int[N+1];
        for(int i=1; i<N+1; i++){
            minDists[i] = Integer.MAX_VALUE;
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2){
                return Integer.compare(n1.distance, n2.distance);
            }
        });
        
        minDists[1] = 0;
        pq.add(new Node(1,0));
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int index = cur.index;
            int distance = cur.distance;
            
            if(minDists[index] < distance){
                continue;
            }
            
            for(Node n : villages[index]){
                if(distance + n.distance < minDists[n.index]){
                    minDists[n.index] = distance + n.distance;
                    pq.add(new Node(n.index, distance + n.distance));
                }
            }
        }
        
        for(int i=1; i<N+1; i++){
            if(minDists[i] <= K){
                answer++;
            }
        }

        return answer;
    }
    
    public class Node{
        int index;
        int distance;
        
        public Node(int index, int distance){
            this.index = index;
            this.distance = distance;
        }
    }
}