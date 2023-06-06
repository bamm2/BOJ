import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Number implements Comparable<Number>{
        int num,abs;
        Number(int num,int abs){
            this.num=num;
            this.abs=abs;
        }

        @Override
        public int compareTo(Number o) {
            return this.abs==o.abs ? Integer.compare(this.num,o.num) : this.abs-o.abs;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();

        int N =Integer.parseInt(br.readLine());

        PriorityQueue<Number> pq= new PriorityQueue<>();

        for(int i=0;i<N;i++){
            int num =Integer.parseInt(br.readLine());
            if(num!=0) pq.offer(new Number(num,Math.abs(num)));
            else{
                if(pq.isEmpty()) sb.append(0).append('\n');
                else sb.append(pq.poll().num).append('\n');
            }

        }

        System.out.println(sb.toString().trim());

    }
}
