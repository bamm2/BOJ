import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
         
        PriorityQueue<Integer> pq =new PriorityQueue<>();

            for(int i=0;i<scoville.length;i++){
                pq.add((scoville[i]));
            }

            int cnt = 0;
            while(pq.size()!=1){
                if(pq.peek()>=K) break;
                int min=pq.poll();
                int small=pq.poll()*2;
                pq.offer(min+small);
                cnt++;
            }

            if(pq.peek()<K) cnt= -1;
            System.out.println(cnt);
            return cnt;
    }
}