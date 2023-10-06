import java.util.*;

/*
소요시간이 작은걸 먼저 처리함.
*/

/*
비어있는 경우때문에, 처음에 jobs 정렬할 때 실행시간으로도 정렬 해줘야됨.
*/

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int len = jobs.length;
        
        // jobs 1.요청시점 2.소요시간으로 정렬 (비어있는 경우 때문에).
        Arrays.sort(jobs, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                // if(o1[0] == o2[0]){
                //     return Integer.compare(o1[1],o2[1]);
                // }
                return Integer.compare(o1[0],o2[0]);
            }
        });
            
        // 확인.
        // for(int[] job : jobs){
        //     System.out.println(Arrays.toString(job));
        // }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return Integer.compare(o1[1], o2[1]);
            }
        });
        
        int end = 0; // 앞 작업이 끝나는 시간.
        int cnt = 0; // 작업을 몇 개 처리했는지.
        int jobIdx = 0; // job의 index.
        
        // 모든 작업을 처리할 때까지 반복.
        while(cnt < len){
            // 앞 작업의 종료시간보다 작거나 같은 요청시점을 가진 작업들을 PQ에 넣어줌.
            while(jobIdx < len && jobs[jobIdx][0] <= end){
                pq.add(jobs[jobIdx++]);
            }
            
            // PQ가 비어있으면 간격을 띄우고 요청이 들어오므로 end 업데이트.
            if(pq.isEmpty()){
                end = jobs[jobIdx][0];
            }
            // 비어있지 않으면, 작업 처리.
            else{
                int[] cur = pq.poll();
                // 정답 += end - 요청시간 + 소요시간
                answer += end - cur[0] + cur[1];
                // end = end + 소요시간
                end += cur[1];
                // 작업 카운트.
                cnt++;
            }
        }
        
        return answer / len;
    }
}