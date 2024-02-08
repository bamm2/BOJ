import java.util.*;

class Solution {
    
    static class Job implements Comparable<Job> {
        
        int startTime,workTime;
        
        Job(int s,int w){
            this.startTime = s;
            this.workTime = w;
        }
        
        public int compareTo(Job other){
            return Integer.compare(this.startTime,other.startTime);
        }
    }
    
    public int solution(int[][] jobs) {
        int answer = 0;
        
        PriorityQueue<Job> pq =new PriorityQueue<>();
        for(int i=0;i<jobs.length;i++){
            pq.offer(new Job(jobs[i][0],jobs[i][1]));
        }
        
        int wholeTimes = 0;
        int time = 0;
        while(!pq.isEmpty()){
            List<Job> candidates =new ArrayList<>();
            Job candidate = null;
            while(!pq.isEmpty()){
                Job curr = pq.peek();
                if(curr.startTime <= time){
                    candidates.add(pq.poll());
                }else{
                    if(candidates.size()==0){
                        time++;
                    }else{
                       break;
                    }
                }
            }
            int idx = -1;
            int min =Integer.MAX_VALUE;
            for(int i=0;i<candidates.size();i++){
                Job curr = candidates.get(i);
                if(curr.workTime < min ){
                    min = curr.workTime;
                    idx = i;
                }
            }
            Job work = candidates.remove(idx);
            time += work.workTime;
            wholeTimes +=  ( time - work.startTime);
            pq.addAll(candidates);
        }
        
        answer = wholeTimes / jobs.length;
        
        return answer;
    }
}