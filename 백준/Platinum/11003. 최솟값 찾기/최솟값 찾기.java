import java.io.*;
import java.util.*;

public class Main {

    static class Point implements Comparable<Point>{
        int idx,num;
        Point(int idx,int num){
            this.idx=idx;
            this.num=num;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.num,o.num);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        PriorityQueue<Point> pq =new PriorityQueue<>();

        st=new StringTokenizer(br.readLine()," ");
        int n =Integer.parseInt(st.nextToken());
        int l =Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine()," ");
        for(int i=1;i<=n;i++){
            int num =Integer.parseInt(st.nextToken());
            pq.offer(new Point(i,num));
            while(pq.peek().idx<i-l+1){
                pq.poll();
            }
            bw.write(pq.peek().num+" ");
        }

        bw.flush();
        bw.close();
        br.close();

    }
}
