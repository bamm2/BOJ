import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Meeting implements Comparable<Meeting>{
        int time;
        boolean isStart;

        public Meeting(int time,boolean isStart){
            this.time=time;
            this.isStart=isStart;
        }

        @Override
        public int compareTo(Meeting o) {
            return Integer.compare(time,o.time);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        PriorityQueue<Meeting> pq=new PriorityQueue<>();

        int N=Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            pq.add(new Meeting(Integer.parseInt(st.nextToken()),true));
            pq.add(new Meeting(Integer.parseInt(st.nextToken()),false));
        }

        int meetingRoom = 0;
        int ans=0;

        while(!pq.isEmpty()){
            Meeting curr=pq.poll();

            if(curr.isStart){
                meetingRoom++;
                ans=Math.max(ans,meetingRoom);
            }else{
                meetingRoom--;
            }
        }
        System.out.println(ans);

    }
}
