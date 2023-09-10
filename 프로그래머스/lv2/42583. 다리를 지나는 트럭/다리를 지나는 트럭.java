import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    
     static final int ZERO = 0;
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
       
             Queue<Integer> q = new ArrayDeque<>();

            int time = 0;
            int currWeight = 0;
            for (int i = 0; i < truck_weights.length; i++) {
                int curr = truck_weights[i];

                if (currWeight + curr <= weight) {
                    if (q.size() >= bridge_length) currWeight -= q.poll();

                    q.offer(curr);
                    currWeight += curr;
                    time++;
                } else {
                    if (q.size() < bridge_length) {
                        while (q.size() < bridge_length) {
                            q.offer(ZERO);
                            time++;
                        }
                        while (true) {
                            currWeight-=q.poll();
                            if (currWeight + curr <= weight) break;
                            time++;
                            q.offer(ZERO);
                        }
                        q.offer(curr);
                        currWeight += curr;
                        time++;
                    } else {
                        while (true) {
                            currWeight -= q.poll();
                            if (currWeight + curr <= weight) break;
                            time++;
                            q.offer(ZERO);
                        }
                        time++;
                        q.offer(curr);
                        currWeight += curr;
                    }
                }
            }

            time+=bridge_length;

            return time;
    }
}