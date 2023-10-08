import java.util.*;

class Solution {
     
         static class InTime implements Comparable<InTime> {
            int st, ed;

            InTime(int st, int ed) {
                this.st = st;
                this.ed = ed;
            }

            @Override
            public int compareTo(InTime o) {
                if(this.st==o.st){
                    return Integer.compare(this.ed,o.ed);
                }else{
                    return Integer.compare(this.st,o.st);
                }
            }
        }

        static class OutTime implements Comparable<OutTime> {
            int st, ed;

            OutTime(int st, int ed) {
                this.st = st;
                this.ed = ed;
            }

            @Override
            public int compareTo(OutTime o) {
                if(this.ed == o.ed){
                    return Integer.compare(this.st,o.st);
                }else{
                    return Integer.compare(this.ed,o.ed);
                }
            }
        }

        public int solution(String[][] book_time) {

            PriorityQueue<InTime> inTimePQ = new PriorityQueue<>();

            for (int i = 0; i < book_time.length; i++) {
                String[] start = book_time[i][0].split(":");
                int st = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
                String[] end = book_time[i][1].split(":");
                int ed = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1])+10;
                inTimePQ.offer(new InTime(st, ed));
            }

            PriorityQueue<OutTime> outTimePQ =new PriorityQueue<>();

            int ans = 1;
            InTime curr = inTimePQ.poll();
            outTimePQ.offer(new OutTime(curr.st,curr.ed));
            while (!inTimePQ.isEmpty()) {
                InTime poll = inTimePQ.poll();
                if(outTimePQ.peek().ed <= poll.st){ // 제일 먼저 종료되는 회의시간+청소시간 보다 시작시작이 늦을 경우
                    while(!outTimePQ.isEmpty()){
                        outTimePQ.poll(); // 회의 종료 시키고 현재 회의 진행시키기
                        if(!outTimePQ.isEmpty() && outTimePQ.peek().ed+10>poll.st) break;
                    }
                }
                outTimePQ.offer(new OutTime(poll.st, poll.ed)); // 회의 넣기
                if(outTimePQ.size()>ans) ans =outTimePQ.size();
            }
            return ans;
        }
}