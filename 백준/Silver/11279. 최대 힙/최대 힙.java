import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();

        int N =Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq =new PriorityQueue<>((n1,n2)->Integer.compare(n2,n1));

        while(N-->0){
            int num =Integer.parseInt(br.readLine());

            if(num==0) {
                if (pq.isEmpty()) sb.append('0').append('\n');
                else {
                    sb.append(pq.poll()).append('\n');
                }
            }else{
                pq.offer(num);
            }

        }

        System.out.println(sb);

    }
}