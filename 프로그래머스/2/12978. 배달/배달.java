import java.util.*;

class Solution {
    
    List<Node>[] graph;
    
    public int solution(int N, int[][] roads, int K) {
        int answer = 0;
        
        graph = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] road : roads) {
            int a = road[0];
            int b = road[1];
            int c = road[2];
            
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }
        
        int[] dist = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        
        dijkstra(dist);
        
        for (int i = 1; i < N + 1; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }

        return answer;
    }
    
    void dijkstra(int[] dist) {

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            
            @Override
            public int compare(Node n1, Node n2) {
                return Integer.compare(n1.dist, n2.dist);
            }
        });
        
        
        pq.add(new Node(1, 0));
        dist[1] = 0;
        
        while(!pq.isEmpty()) {
            Node curNode = pq.poll();
            
            if (curNode.dist != dist[curNode.node]) {
                continue;
            }
            
            for(Node nextNode : graph[curNode.node]) {
                int newDist = curNode.dist + nextNode.dist;
                
                if (newDist < dist[nextNode.node]) {
                    pq.add(new Node(nextNode.node, newDist));
                    dist[nextNode.node] = newDist;
                }
            }
        }
    }
    
    class Node {
        int node;
        int dist;
        
        public Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }
}