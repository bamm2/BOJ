import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
            Queue<Integer> q1 =new ArrayDeque<>();
            Queue<Integer> q2 =new ArrayDeque<>();
            long q1Sum = 0;
            long q2Sum = 0;

            int size =queue1.length;

            for(int i=0;i<size;i++){
                q1.offer(queue1[i]);
                q2.offer(queue2[i]);
                q1Sum+=queue1[i];
                q2Sum+=queue2[i];
            }

            int cnt = 0 ;

            while(q1Sum!=q2Sum){
                if(q1Sum>q2Sum){
                    Integer poll = q1.poll();
                    q1Sum-=poll;
                    q2Sum+=poll;
                    q2.offer(poll);
                }else{
                    Integer poll = q2.poll();
                    q2Sum-=poll;
                    q1Sum+=poll;
                    q1.offer(poll);
                }

                cnt++;
                if( (q1.size()==0 || q2.size()==0) || cnt>size*3){
                    cnt=Integer.MAX_VALUE;
                    break;
                }
            }

            if(cnt==Integer.MAX_VALUE) cnt = -1;
            return cnt;
    }
}