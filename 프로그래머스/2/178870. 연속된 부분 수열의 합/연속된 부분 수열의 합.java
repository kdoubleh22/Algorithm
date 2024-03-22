class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        
        int left = 0;
        int right = 0;
        int sum = sequence[0];
        int min = Integer.MAX_VALUE;
        
        while (true) {
            if (sum == k) {
                if (right - left + 1 < min) {
                    min = right - left + 1;
                    answer[0] = left;
                    answer[1] = right;
                }
                sum -= sequence[left];
                left++;
                right++;
                if (right == sequence.length) break;
                sum += sequence[right];
            } else if (sum > k) {
                sum -= sequence[left];
                left++;
            } else { // sum < K
                right++;
                if (right == sequence.length) break;
                sum += sequence[right];
            }
        }
        
        return answer;
    }
}