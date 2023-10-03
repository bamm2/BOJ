import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");

        int N =Integer.parseInt(st.nextToken());
        int m =Integer.parseInt(st.nextToken());

        PriorityQueue<Long> pq =new PriorityQueue<>();
        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++){
            pq.offer(Long.parseLong(st.nextToken()));
        }

        while (m-->0){
            long add =(long)pq.poll() + pq.poll();
            pq.offer(add);
            pq.offer(add);
        }

        long sum =0;
        while (!pq.isEmpty()){
            sum+=pq.poll();
        }

        System.out.println(sum);
    }
}