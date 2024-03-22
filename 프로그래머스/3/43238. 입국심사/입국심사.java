class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        long left = 1;
        long right = (long)1e18;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            System.out.println("mid:"+mid);
            
            if (f(mid, n, times)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
    
    boolean f(long mid, int n, int[] times) {
        long sum = 0;
        
        for (int i = 0; i < times.length; i++) {
            sum += mid / times[i];
        }
        
        return sum >= n;
    }
}