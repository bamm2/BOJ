import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq =new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        });

        for(int i=0;i<N;i++){
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int sum=0;
        if(pq.size()!=1) {
            while (true) {
                int num1 = pq.poll();
                int num2 = pq.poll();
                sum += num1 + num2;
                pq.offer(num1 + num2);
                if (pq.size() == 1) break;
            }
        }else{
            sum=0;
        }
        System.out.println(sum);
    }
}
