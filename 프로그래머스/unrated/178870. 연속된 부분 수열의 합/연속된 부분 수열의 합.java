import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int[] solution(int[] sequence, int k) {
              int[] answer = new int[2];

            Queue<Integer> q = new ArrayDeque<>();
            int sum = 0;
            int minDiff = Integer.MAX_VALUE;
            int startIdx = 0;
            int endIdx = -1;
            for (int i = 0; i < sequence.length; i++) {
                int curr = sequence[i];
                q.offer(curr);
                sum+=curr;
                endIdx++;

                if(sum<k) continue;

                while (true){
                    if(sum==k) {
                        if(endIdx-startIdx<minDiff){
                            minDiff=endIdx-startIdx;
                            answer[0]=startIdx;
                            answer[1]=endIdx;
                        }
                        break;
                    }
                    Integer poll = q.poll();
                    sum-=poll;
                    startIdx++;

                    if(sum<k) break;
                }
            }

            return answer;
    }
}