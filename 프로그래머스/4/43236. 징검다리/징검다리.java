import java.util.*;

/*
1.
가장 간격이 좁은 바위를 계속 제거해나가면 되지 않을까?
근데 간격이란게 왼쪽 바위, 오른쪽 바위가 있음.
같은 최소거리일 때 어떤걸 지워야할지?
바위를 정렬하는데, 왼쪽 오른쪽 거리중 작은걸로 정렬하고, 같을경우 합이 작은걸로.
PQ.
하나 뽑으면 관련 거리를 다시 업데이트 해야되는구나.
이 방법은 지금의 선택이 나중에 최선의 결과라는 보장이 없음.

2.
문제 의도대로 이분탐색으로.
*/

/*
예시
0, 2, 11, 14, 17, 21, 25
2, 9, 3, 3, 4, 4

2 삭제
0, 11, 14, 17, 21, 25
11, 3, 3, 4, 4

2, 11 삭제
0, 14, 17, 21, 25
14, 3, 4, 4

2, 14 삭제
0, 11, 17, 21, 25
11, 6, 4, 4
*/

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        // rocks 정렬.
        Arrays.sort(rocks);
        
        // 최소 거리는 1, 최대 거리는 distance.
        int left = 0;
        int right = distance+1;
        
        
        while(left <= right){
            // mid가 바위 간 최소거리라고 가정. 그래서 mid 보다 거리가 작은 돌은 전부 제거.
            // 이게 어렵다.
            int mid = (left + right) / 2;
            
            //
            // System.out.println("left:"+left+" right:"+right+" mid:"+mid);
            
            int cnt = 0;
            int prevRock = 0; // 이전 돌 위치
            for(int i=0; i<rocks.length; i++){
                // mid가 커지면 cnt도 커진다.
                if(rocks[i] - prevRock < mid){
                    cnt++;
                    continue; // 중요.
                }
                prevRock = rocks[i];
            }
            
            if(distance - prevRock < mid){
                cnt++;
            }
            
            //
            // System.out.println("cnt:"+cnt);
            
            // 삭제된 돌의 개수가 n개이면 정답 업데이트.
            // 항상 cnt == n이 되는 경우가 없을수도 있다.
            // 왜냐하면 거리의 중복이 있을 수 있기 때문.
            // 그래서 cnt >= n 인 제일 작은 mid가 답.
            if(cnt <= n){
                answer = mid;
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
            
            //
            // System.out.println("answer:"+answer);
        }
        
        
        return answer;
    }
}