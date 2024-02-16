class Solution {
    
    int[] times;
    
    public long solution(int n, int[] mTimes) {
        long answer = -1;
        times = mTimes;
        
        long left = 1;
        long right = (long) 1e14;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            
            if (f(mid) >= n) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
    
    long f(long mid) {
        long ret = 0;
        
        for (int time : times) {
            ret += mid / time;
        }
        
        return ret;
    }
}