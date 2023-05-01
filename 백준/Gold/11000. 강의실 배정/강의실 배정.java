import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Meeting implements Comparable<Meeting> {
        int start, end;
        Meeting(int start,int end){
            this.start=start;
            this.end=end;
        }

        @Override
        public int compareTo(Meeting o) {
            if(this.start==o.start){
                return this.end-o.end;
            }else{
                return this.start-o.start;
            }
        }

    }

    static Meeting[] meetings;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        meetings=new Meeting[N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            meetings[i]=new Meeting(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(meetings);

        Queue<Integer> q=new PriorityQueue<>();
        q.offer(meetings[0].end);

        for(int i=1;i<N;i++){
            Meeting curr = meetings[i];

           if(curr.start >= q.peek() ){
              q.poll();
           }

           q.offer(curr.end);
        }

        System.out.println(q.size());

    }
}