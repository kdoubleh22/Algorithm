class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        long left = 1;
        long right = (long) 1e18;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            
            if (f(mid, times) >= n) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
    
    long f(long mid, int[] times) {
        long ret = 0;
        
        for (int time : times) {
            ret += mid / time;
        }
        
        return ret;
    }
}