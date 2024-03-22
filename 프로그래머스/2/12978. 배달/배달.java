import java.util.*;

class Solution {
    List<Node>[] graph;
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < road.length; i++) {
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];
            
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }
        
        int[] dist = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dijkstra(dist);
        
        for(int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                ++answer;
            }
        }

        return answer;
    }
    
    void dijkstra(int[] dist) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2) {
                return Integer.compare(n1.cost, n2.cost);
            }
        });
        
        pq.add(new Node(1, 0));
        dist[1] = 0;
        
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            
            if (curNode.cost != dist[curNode.node]) {
                continue;
            }
            
            for (Node nextNode : graph[curNode.node]) {
                int newDist = curNode.cost + nextNode.cost;
                if (newDist < dist[nextNode.node]) {
                    dist[nextNode.node] = newDist;
                    pq.add(new Node(nextNode.node, newDist));
                }
            }
        }
    }
    
    class Node {
        int node;
        int cost;
        
        public Node (int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
}